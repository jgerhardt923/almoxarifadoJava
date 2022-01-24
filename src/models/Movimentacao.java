package src.models;

import java.sql.Connection;

public class Movimentacao extends Model {
    public Movimentacao(Connection connection) {
        this.connection = connection;
        this.tableName = "Movimentacao";
    }
}
