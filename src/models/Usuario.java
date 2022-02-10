package src.models;

import java.sql.Connection;
import java.util.Arrays;

public class Usuario extends Model {
    public Usuario(Connection connection) {
        this.connection = connection;
        this.tableName = "Usuario";
        this.fields = Arrays.asList("nome","senha");
    }
}
