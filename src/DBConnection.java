import java.sql.*;

public class DBConnection {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost/Wypozyczalnia";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "admin";

    public static Connection DBConnect() {

        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER); // ladujemy sterownik
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        //    System.out.println("polaczono");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void DodajKlienta(String imie2, String nazwisko2)  {

        try {
            Connection connection = DBConnection.DBConnect();
            Statement statement = connection.createStatement();

            //INSERT DATA
            String Insertquery = "INSERT INTO klient (imie,nazwisko)" + "VALUES(?,?)";
           // String Insertquery2 = "INSERT INTO klient ( imie, nazwisko) VALUES ('AAA222','BB22')";
            PreparedStatement preStat = connection.prepareStatement(Insertquery);

          //  String Imie = FieldImie.getText();
          //  String Nazwisko = FieldNazwisko.getText();
          //  preStat.setInt(1, lastID+1);
            preStat.setString(1, imie2);
            preStat.setString(2, nazwisko2);

            preStat.executeUpdate();
          //  statement.executeUpdate(Insertquery2);

            statement.close();
            connection.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void WypozyczAuto(int dni, int car_id) {

        try {
            Connection connection = DBConnection.DBConnect();
            Statement statement = connection.createStatement();

            //find last id_klienta
             String select_id_klienta = "select max(id_klient) from klient";
             ResultSet rs = statement.executeQuery(select_id_klienta);
             rs.next();
             int lastKlient_id = rs.getInt(1);


             //find last id_wypozyczenia
            String select_id_wypozyczenia = "select max(id_wypozyczenia) from wypozyczenia";
            ResultSet rs2 = statement.executeQuery(select_id_wypozyczenia);
            rs2.next();

                int lastId_wypozyczenia = rs2.getInt(1);


                //INSERT DATA
                String Insertquery = "INSERT INTO wypozyczenia " +
                        "(id_wypozyczenia, klient_id, samochody_id, dlugosc_wypozyczenie)" +
                        "VALUES (?,?,?,?)";
                // String Insertquery2 = "INSERT INTO klient ( imie, nazwisko) VALUES ('AAA222','BB22')";
                PreparedStatement preStat = connection.prepareStatement(Insertquery);

                //  String Imie = FieldImie.getText();
                //  String Nazwisko = FieldNazwisko.getText();
                //  preStat.setInt(1, lastID+1);
                preStat.setInt(1, ++lastId_wypozyczenia);
                preStat.setInt(2, lastKlient_id);
                preStat.setInt(3, car_id);
                preStat.setInt(4, dni);

                preStat.executeUpdate();
                //  statement.executeUpdate(Insertquery2);

            //UPDATE DOSTEPNOSC SAMOCHODY

            String UpdateDostepnosc = "UPDATE samochody set taknie= \'nie\' where  id_samochody=" + car_id ;
          //  ResultSet rs3 = statement.executeQuery(UpdateDostepnosc);
            statement.executeUpdate(UpdateDostepnosc);

                statement.close();
                connection.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    public static void UsunAuto( int car_id) {

        try {
            Connection connection = DBConnection.DBConnect();
            Statement statement = connection.createStatement();

                //INSERT DATA
                String DeleteCar = "DELETE FROM samochody WHERE id_samochody= ?";

                PreparedStatement preStat2 = connection.prepareStatement(DeleteCar);

                preStat2.setInt(1, car_id);
              // statement.executeUpdate(DeleteCar);
                preStat2.execute();

                statement.close();
                connection.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}


