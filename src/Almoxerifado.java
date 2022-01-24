package src;
import java.sql.Connection;
import java.sql.DriverManager;

public class Almoxerifado{
    private static Connection connection;
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:dataBase/db.sqlite");
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}