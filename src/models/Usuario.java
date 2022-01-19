package src.models;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Usuario {
    Connection connection;
    String tableName;
    ArrayList<String> fields;

    public Usuario(Connection connection) {
        this.connection = connection;
    }

    public void create(HashMap<String, Object> data){
        String sql = "INSERT INTO"+tableName+"(";
        String valuesString = "VALUES (";
        for (String string : fields) {
            sql += string+",";
            valuesString += data.get(string);
        }
        //sql
        try {
            Statement preStatement = connection.createStatement();
            preStatement.execute(sql);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void delete(Integer id){

    }

    public void update(Integer id, HashMap<String, Object> data){

    }

    public void getOne(HashMap<String, Object> data){

    }

    public void getAll() {
        
    }

    public void getAll(HashMap<String, Object> data) {
        
    }
}
