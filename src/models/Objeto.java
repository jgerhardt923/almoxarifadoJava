package src.models;

import java.sql.Connection;

public class Objeto extends Model {
    public Objeto(Connection connection) {
        this.connection = connection;
        this.tableName = "Objeto";
    }
}
