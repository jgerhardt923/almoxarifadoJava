package src.models;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;

public class Usuario {
    Connection connection;

    public Usuario(Connection connection) {
        this.connection = connection;
    }

    public String stringFromHashMap(HashMap<String, Object> hashMap, String separetor, String mode) {
        String string = "";
        if (mode == "k" | mode == "key") {
            for (String keyString : hashMap.keySet()) {
                string += keyString +" "+ separetor + " ";
            }
            return string.substring(0, (-1 * separetor.length())-1);
        }
        if (mode == "v" | mode == "value") {
            for (String keyString : hashMap.keySet()) {
                string += "'" + String.valueOf(hashMap.get(keyString)) +"' "+ separetor + " ";
            }
            return string.substring(0, (-1 * separetor.length())-1);
        }
        if (mode == "k=v" | mode == "key=value") {
            for (String keyString : hashMap.keySet()) {
                string += keyString + "='" + String.valueOf(hashMap.get(keyString)) +"' "+ separetor + " ";
            }
            return string.substring(0, (-1 * separetor.length())-1);
        }
        return null;
    }

    /**
     * Insere um novo objeto no banco de dados
     * @param data
     * deve receber um HashMap onde <b>key</b> é o nome do campo e <b>value</b> é o valor do campo 
     */
    public void create(HashMap<String, Object> data){
        try {
            Statement statement = connection.createStatement();
            statement.execute("insert into Usuario("+ stringFromHashMap(data, ",", "k") +") values("+ stringFromHashMap(data, ",", "v") +");");
            System.out.println(data.get("nome")+" registrado");
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Integer id){
        try {
            Statement statement = connection.createStatement();
            statement.execute("delete from Usuario where id="+String.valueOf(id)+";");
            System.out.println("usuario "+id+" apagado!");
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Integer id, HashMap<String, Object> data){
        String kString = stringFromHashMap(data, ",", "k=v");
        try {
            Statement statement = connection.createStatement();
            statement.execute("update Usuario set "+kString+" where id="+id+";");
            System.out.println("usuario "+id+" atualizado!");
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getOne(HashMap<String, Object> data){  

    }

    public void getAll() {
        
    }

    public void getAll(HashMap<String, Object> data) {
        
    }
}
