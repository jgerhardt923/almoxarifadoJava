package src.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Model {
    String tableName;
    List<String> fields;
    Connection connection;

    /**
     * retorna uma string com os dados do <b>hashMap</b>
     * @param hashMap
     * HashMap contendo os dados
     * @param separator
     * String para separar os dados
     * @param mode
     * <p>string representando o modo como a string sera retornada, pode ser:</p>
     * <p><b>k</b> | <b>key</b> para retornar apenas as chaves do hashMap</p>
     * <p><b>v</b> | <b>value</b> para retornar apenas os valores do hashMap</p>
     * <p><b>k=v</b> | <b>key=value</b> para retornar os pares <b>chave=valor</b> do hashMap</p>
     * @return
     * <b>String</b> ou <b>null</b>
     */
    public String stringFromHashMap(HashMap<String, Object> hashMap, String separator, String mode) {
        String string = "";
        if (mode == "k" | mode == "key") {
            for (String keyString : hashMap.keySet()) {
                string += keyString +" "+ separator + " ";
            }
            return string.substring(0, string.length() - (separator.length() + 1));
        }
        if (mode == "v" | mode == "value") {
            for (String keyString : hashMap.keySet()) {
                string += "'" + String.valueOf(hashMap.get(keyString)) +"' "+ separator + " ";
            }
            return string.substring(0, string.length() - (separator.length() + 1));
        }
        if (mode == "k=v" | mode == "key=value") {
            for (String keyString : hashMap.keySet()) {
                string += keyString + "='" + String.valueOf(hashMap.get(keyString)) +"' "+ separator + " ";
            }
            return string.substring(0, string.length() - (separator.length() + 1));
        }
        return null;
    }

    /**
     * Retorna um ArrayList dos dados do rSet
     * @param rSet
     *      ResultSet retornado da query
     * @return
     *      ArrayList com HashMaps contendo os dados da query
     */
    public ArrayList<HashMap<String, Object>> hashMapFromTable(ResultSet rSet) {
        try {
            ArrayList<HashMap<String, Object>> rows = new ArrayList<>();
            ResultSetMetaData metaData = rSet.getMetaData();
            int nColunas = metaData.getColumnCount();

            while (rSet.next()) {
                HashMap<String, Object> row = new HashMap<>();
                for (int i = 1; i <= nColunas; i++) {
                    row.put(metaData.getColumnName(i), rSet.getObject(i));
                }
                rows.add(row);
            }

            return rows;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void create(HashMap<String, Object> data){
        try {
            Statement statement = connection.createStatement();
            statement.execute("insert into "+ tableName +"("+ stringFromHashMap(data, ",", "k") +") values("+ stringFromHashMap(data, ",", "v") +");");
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Integer id){
        try {
            Statement statement = connection.createStatement();
            statement.execute("delete from "+ tableName +" where id="+String.valueOf(id)+";");
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Integer id, HashMap<String, Object> data){
        String kString = stringFromHashMap(data, ",", "k=v");
        try {
            Statement statement = connection.createStatement();
            statement.execute("update "+ tableName +" set "+kString+" where id="+id+";");
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public HashMap<String, Object> getOne(HashMap<String, Object> filter){
        String kString = stringFromHashMap(filter, "and", "k=v");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from "+ tableName +" where "+kString+" limit 1;");
            ArrayList<HashMap<String, Object>> res =  hashMapFromTable(resultSet);
            statement.close();
            return res.get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<HashMap<String, Object>> getAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from "+ tableName +";");
            ArrayList<HashMap<String, Object>> res =  hashMapFromTable(resultSet);
            statement.close();
            return res;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<HashMap<String, Object>> getAll(HashMap<String, Object> filter) {
        String kString = stringFromHashMap(filter, "and", "k=v");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from "+ tableName +" where "+kString+" ;");
            ArrayList<HashMap<String, Object>> res =  hashMapFromTable(resultSet);
            statement.close();
            return res;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
