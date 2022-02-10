package src.models;

import java.sql.Connection;
import java.util.Arrays;

public class Categoria extends Model{
    public Categoria(Connection connection) {
        this.connection = connection;
        this.tableName = "Categoria";
        this.fields = Arrays.asList("nome","descricao");
    }
}
