package src.models;

import java.sql.Connection;

public class Usuario extends Model {
    public Usuario(Connection connection) {
        this.connection = connection;
        this.tableName = "Usuario";
    }
}
