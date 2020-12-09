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

            //find last id
          //  String query = "select count(*) from klient";
          //  ResultSet rs = statement.executeQuery(query);

           // rs.next();
          //  int lastID= rs.getInt(1);

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
}


