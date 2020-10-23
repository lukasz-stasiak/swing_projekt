import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost/Pracownicy";
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

}


