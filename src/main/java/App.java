import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/MailRuHW";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD))
        {
            Statement statement = connection.createStatement();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
