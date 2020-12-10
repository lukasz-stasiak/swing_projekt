import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class MyPanel extends JPanel implements ActionListener {

    JTextField FieldImie, FieldNazwisko, FieldDni, FieldCena;
    JLabel LabelImie, LabelNazwisko, LabelDni, LabelCena, LabelSamochody, LabelWypozyczone, LabelKlient;
    JButton bOddaj, bWypozycz, bSelect, bAddKlient;
    JTable TableSamochody, TableWypozyczone;
    JPanel panel;

    static ArrayList<Samochody> getUsers() {

        ArrayList<Samochody> ALsamochody = new ArrayList<Samochody>();


        try {
            Connection connection = DBConnection.DBConnect();
            Statement statement = connection.createStatement();
            String query = "select * from samochody";
            ResultSet rs = statement.executeQuery(query);


            while (rs.next()) {
                Samochody samochody = new Samochody(
                        rs.getInt("id_samochody"),
                        rs.getString("nazwa"),
                        rs.getString("taknie")
                );
                ALsamochody.add(samochody);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ALsamochody;
    }

    static ArrayList<Wypozyczenia> getWypozyczenia() {

        ArrayList<Wypozyczenia> ALwypozyczenia = new ArrayList<Wypozyczenia>();


        try {
            Connection connection = DBConnection.DBConnect();
            Statement statement = connection.createStatement();
            String query_wypozyczenia = "SELECT imie, nazwisko, nazwa, dlugosc_wypozyczenie FROM wypozyczenia \n" +
                    "INNER JOIN klient ON wypozyczenia.klient_id=klient.id_klient\n" +
                    "INNER JOIN samochody ON samochody.id_samochody=wypozyczenia.samochody_id";
            ResultSet rs3 = statement.executeQuery(query_wypozyczenia);

            while (rs3.next()) {
                Wypozyczenia wypozyczenia = new Wypozyczenia(
                        rs3.getString("imie"),
                        rs3.getString("nazwisko"),
                        rs3.getString("nazwa"),
                        rs3.getInt("dlugosc_wypozyczenie")
                );
                ALwypozyczenia.add(wypozyczenia);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ALwypozyczenia;
    }


    public MyPanel() {

        // TextFields Imie, Nazwisko, Ilosc Dni , Cena

        setLayout(null); // wybor menagera tutaj zaden
// SEKCJA SAMOCHODY Dostepne do wypozyczenia:

        LabelSamochody = new JLabel("Samochody dostepne");
        LabelSamochody.setBounds(100, 50, 200, 20);
        add(LabelSamochody);

        // SEKCJA SAMOCHODY WYPOZYCZONE

        LabelWypozyczone = new JLabel("Wypozyczone samochody");
        LabelWypozyczone.setBounds(900, 50, 200, 20);
        add(LabelWypozyczone);

        //SEKCJA DO WPISYWANIA KLIENTA I WYPOZYCZENIA

        LabelKlient = new JLabel("Klient");
        LabelKlient.setBounds(500, 50, 200, 20);
        add(LabelKlient);

        LabelImie = new JLabel("Imie");
        LabelImie.setBounds(600, 150, 100, 20);
        //  add(LabelImie);

        int xField = 400;
        FieldImie = new JTextField("Imie");
        FieldImie.setBounds(xField, 150, 200, 20);
        add(FieldImie);

        FieldNazwisko = new JTextField("Nazwisko");
        FieldNazwisko.setBounds(xField, 200, 200, 20);
        add(FieldNazwisko);

        FieldDni = new JTextField("Ilość Dni");
        FieldDni.setBounds(xField, 250, 200, 20);
        add(FieldDni);

        FieldCena = new JTextField("Cena");
        FieldCena.setBounds(xField, 300, 200, 20);
        // add(FieldCena);

        bWypozycz = new JButton("Wypozycz Auto");
        bWypozycz.setBounds(400, 400, 150, 30);
        add(bWypozycz);
        bWypozycz.addActionListener(this);

       // bAddKlient = new JButton("Dodaj Klienta");
        //bAddKlient.setBounds(300, 350, 150, 30);
       // add(bAddKlient);
       // bAddKlient.addActionListener(this);

// SEKCJA ODDAJ AUTO
        bOddaj = new JButton("Oddaj Auto");
        bOddaj.setBounds(800, 400, 150, 30);
        add(bOddaj);

        bSelect = new JButton("Pokaz zaznaczone auto");
        bSelect.setBounds(100, 400, 250, 30);
        add(bSelect);
        bSelect.addActionListener(this);

        //button.setText("Kliknij");
        //dispose() wyjscie po kliknieciu


        // WYSWIETLENIE W JTABLE AUT
        DodajSamochodyDoTabeli();
        // WYSWIETLENIE W JTABLE WYPOZYCZONE
        DodajWypozyczoneDoTabeli();

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane(TableSamochody);

        panel.add(pane, BorderLayout.CENTER);

        TableSamochody.setBounds(20, 100, 300, 250);
        add(TableSamochody);

        TableWypozyczone.setBounds(800, 100, 300, 250);
        add(TableWypozyczone);


    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1200, 600);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (bSelect.hasFocus()) {
            int RowNumber = TableSamochody.getSelectedRow();
            String a = TableSamochody.getValueAt(RowNumber, 1).toString();
            JOptionPane.showMessageDialog(null, a);
        } else if (bWypozycz.hasFocus()) {
            // String Dni = FieldDni.getText();
            //  String Imie = FieldImie.getText();
            //  String Nazwisko = FieldNazwisko.getText();

            int SelectedRow = TableSamochody.getSelectedRow();
            String SelectedCarID = TableSamochody.getValueAt(SelectedRow, 0).toString();
            String SelectedCarName = TableSamochody.getValueAt(SelectedRow, 1).toString();
            String Dostepnosc = TableSamochody.getValueAt(SelectedRow, 2).toString();

            if (Dostepnosc.equals("tak")) {
                DBConnection.DodajKlienta(FieldImie.getText(), FieldNazwisko.getText());

                int id_car = Integer.parseInt(SelectedCarID);

                int ilosc_dni = Integer.parseInt(FieldDni.getText());
                DBConnection.WypozyczAuto(ilosc_dni, id_car);


            }
            else {
                JOptionPane.showMessageDialog(null, "Samochód Niedostępny, wybierz tylko auta dostępne");
            }


            //   JOptionPane.showMessageDialog(null, SelectedCarID);
        } else if (bOddaj.hasFocus()) {
            int SelectedRow2 = TableWypozyczone.getSelectedRow();
            String SelectedCarID = TableWypozyczone.getValueAt(SelectedRow2, 0).toString();
           // DBConnection.OddajAuto();
        }

    }

    private void DodajSamochodyDoTabeli() {
        DefaultTableModel model = new DefaultTableModel();
        Object[] columnsName = new Object[3];
        columnsName[0] = "ID";
        columnsName[1] = "Nazwa";
        columnsName[2] = "Dostepnosc";

        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[3];

        for (int i = 0; i < getUsers().size(); i++) {

            rowData[0] = getUsers().get(i).getId_samochody();
            rowData[1] = getUsers().get(i).getNazwa();
            rowData[2] = getUsers().get(i).getTaknie();

            model.addRow(rowData);
        }
        TableSamochody = new JTable();
        TableSamochody.setModel(model);
    }

    private void DodajWypozyczoneDoTabeli() {
        DefaultTableModel model2 = new DefaultTableModel();
        Object[] columnsName2 = new Object[4];
        columnsName2[0] = "Imie";
        columnsName2[1] = "Nazwisko";
        columnsName2[2] = "Nazwa";
        columnsName2[3] = "IloscDni";

        model2.setColumnIdentifiers(columnsName2);

        Object[] rowData2 = new Object[4];

        for (int i = 0; i < getWypozyczenia().size(); i++) {

            rowData2[0] = getWypozyczenia().get(i).getImie();
            rowData2[1] = getWypozyczenia().get(i).getNazwisko();
            rowData2[2] = getWypozyczenia().get(i).getNazwa();
            rowData2[3] = getWypozyczenia().get(i).getDlugosc_wypozyczenie();

            model2.addRow(rowData2);
        }
        TableWypozyczone = new JTable();
        TableWypozyczone.setModel(model2);
        TableWypozyczone.revalidate();
        TableWypozyczone.repaint();

        // TableWypozyczone.setAutoCreateColumnsFromModel(false);

    }

}





