import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JPanel implements ActionListener {

    JTextField textField;
    JButton button;
    JTable tPracownicy;
    JPanel panel;



    static ArrayList<Pracownicy> getUsers(){

        ArrayList<Pracownicy> ALpracownicy = new ArrayList<Pracownicy>();


        try {
            Connection connection = DBConnection.DBConnect();
            Statement statement = connection.createStatement();
            String query = "select * from pracownicy";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Pracownicy pracownicy = new Pracownicy(
                rs.getString("imie"),
                rs.getString("nazwisko")
                );
                ALpracownicy.add(pracownicy);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ALpracownicy;
    }



    public MyPanel() {
        setLayout(null); // wybor menagera tutaj zaden
        textField = new JTextField();
        textField.setBounds(10, 10, 100, 100);
        add(textField);

        button = new JButton("Kliknij");
        button.setBounds(20, 150, 100, 30);
        add(button);

        //button.setText("Kliknij");
        //dispose() wyjscie po kliknieciu
        button.addActionListener(this);


        DefaultTableModel model = new DefaultTableModel();
        Object[] columnsName = new Object[2];
        columnsName[0] = "Imie";
        columnsName[1] = "Nazwisko";

        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[2];

        for(int i = 0; i < getUsers().size(); i++){

            rowData[0] = getUsers().get(i).getImie();
            rowData[1] = getUsers().get(i).getNazwisko();

            model.addRow(rowData);
        }
        tPracownicy = new JTable();
        panel = new JPanel();

        tPracownicy.setModel(model);

        panel.setLayout(new BorderLayout());

        JScrollPane pane = new JScrollPane(tPracownicy);

        panel.add(pane,BorderLayout.CENTER);

        tPracownicy.setBounds(20, 250, 600, 150);
        add(tPracownicy);


    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1200, 600);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textField.setText("Bum");

    }




}
