package src.models;

import java.sql.Connection;
import java.util.Arrays;

public class Objeto extends Model {
    public Objeto(Connection connection) {
        this.connection = connection;
        this.tableName = "Objeto";
        this.fields = Arrays.asList("nome","descricao", "categoria_id", "quantidade");
    }
}
