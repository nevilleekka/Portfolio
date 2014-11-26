package Database;
import java.sql.*;
/**
 * @author Neville Ekka
 * Assignment 2
 */
public class Connection {
    private static String inital_URL = "jdbc:mysql://";
    private static String server_URL = "sql4.freesqldatabase.com";
    private static String db_name = "sql458531";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String username = "sql458531";
    private static String password = "qD8*qK8!";
    private static java.sql.Connection connector = null;

    public static void Create_Connection()
            throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        connector = DriverManager.getConnection(inital_URL +
              server_URL + "/" + db_name, username, password);
    }

    public static java.sql.Connection get_Connector() {
        return connector;
    }
    
    public static void Close_Connection()
            throws SQLException {
        if (connector != null) {
            connector.close();
        }
    }
}
