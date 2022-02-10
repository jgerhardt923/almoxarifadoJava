package src.models;

import java.sql.Connection;
import java.util.Arrays;

public class Movimentacao extends Model {
    public Movimentacao(Connection connection) {
        this.connection = connection;
        this.tableName = "Movimentacao";
        this.fields = Arrays.asList("sentido", "quantidade", "objeto_id", "usuario_id");
    }
}
