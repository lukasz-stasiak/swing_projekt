import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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


    public static List<Klient> pobierzKlientow() {
        List<Klient> klienci = new ArrayList<>();
        String query = "SELECT id_klient, imie, nazwisko FROM Klient";
        try {
            Connection connection = DBConnection.DBConnect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Klient klient = new Klient(rs.getInt("id_klient")
                        ,rs.getString("imie"),
                        rs.getString("nazwisko"));
                klienci.add(klient);
            }
             //disconnectDB();



        } catch (SQLException e) {
            System.err.println("SQL error " + e.getMessage());
        }

        return klienci;
    }

    public static List<Samochody> pobierzSamochody() {
        List<Samochody> samochodyDane = new ArrayList<>();
        String query = "SELECT ID_Samochody, Nazwa FROM Samochody";
        try {
            Connection connection = DBConnection.DBConnect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            System.out.println("pobierzSamochody - iloc rekordow -" + rs.getFetchSize());
            while (rs.next()) {

                Samochody pojazd = new Samochody(rs.getInt("ID_Samochody")
                        , rs.getString("Nazwa"));
                samochodyDane.add(pojazd);
            }
            //disconnectDB();


        } catch (SQLException e) {
            System.err.println("SQL error " + e.getMessage());
        }

        return samochodyDane;
    }

    public static long DodajKlienta(String imie, String nazwisko)  {

        String record = "INSERT INTO Klient (imie, nazwisko)" +
                "VALUES (?, ?)";

        try {
            Connection connection = DBConnection.DBConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(record, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, imie);
            preparedStatement.setString(2, nazwisko);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }

        } catch (SQLException e) {
                System.out.println("Błąd z pobraniem identyfikatora.");
                e.printStackTrace();
        }

        return -1;
    }


}


