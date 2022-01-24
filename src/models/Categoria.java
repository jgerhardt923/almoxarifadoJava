package src.models;

import java.sql.Connection;

public class Categoria extends Model{
    public Categoria(Connection connection) {
        this.connection = connection;
        this.tableName = "Categoria";
    }
}
