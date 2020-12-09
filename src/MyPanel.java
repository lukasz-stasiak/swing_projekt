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
                        rs.getString("nazwa")
                );
                ALsamochody.add(samochody);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ALsamochody;
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

        bAddKlient = new JButton("Dodaj Klienta");
        bAddKlient.setBounds(300, 350, 150, 30);
        add(bAddKlient);
        bAddKlient.addActionListener(this);

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



        DefaultTableModel model = new DefaultTableModel();
        Object[] columnsName = new Object[2];
        columnsName[0] = "ID";
        columnsName[1] = "Nazwa";

        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[2];

        for (int i = 0; i < getUsers().size(); i++) {

            rowData[0] = getUsers().get(i).getId_samochody();
            rowData[1] = getUsers().get(i).getNazwa();

            model.addRow(rowData);
        }
        TableSamochody = new JTable();
        TableWypozyczone = new JTable();

        panel = new JPanel();

        TableSamochody.setModel(model);

        panel.setLayout(new BorderLayout());

        JScrollPane pane = new JScrollPane(TableSamochody);

        panel.add(pane, BorderLayout.CENTER);

        TableSamochody.setBounds(20, 100, 300, 250);
        add(TableSamochody);

        TableWypozyczone.setBounds(800,100,300, 250 );
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
        }
        else if (bWypozycz.hasFocus()) {
            String Dni = FieldDni.getText();
            String Imie = FieldImie.getText();
            String Nazwisko = FieldNazwisko.getText();

            int SelectedRow = TableSamochody.getSelectedRow();
            String SelectedCar = TableSamochody.getValueAt(SelectedRow, 1).toString();

            JOptionPane.showMessageDialog(null, SelectedCar);
        } else if (bAddKlient.hasFocus()) {
                DBConnection.DodajKlienta(FieldImie.getText(), FieldNazwisko.getText());
        }

    }



    }





