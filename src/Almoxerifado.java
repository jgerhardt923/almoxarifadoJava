package src;
import java.sql.Connection;
import java.sql.DriverManager;

public class Almoxerifado{
    private static Connection connection;
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/ADM/Desktop/almoxerifado/dataBase/db.sqlite");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}