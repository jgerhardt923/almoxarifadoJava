package src;
import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import src.models.Categoria;
import src.models.Movimentacao;
import src.models.Objeto;
import src.models.Usuario;

public class Almoxarifado{
    private static Connection connection;

    private static Console console;
    
    private static Usuario usuario;
    private static Categoria categoria;
    private static Objeto objeto;
    private static Movimentacao movimentacao;

    private static HashMap usuarioLogado = null;

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:dataBase/db.sqlite");
            
            console = System.console();

            usuario = new Usuario(connection);
            categoria = new Categoria(connection);
            objeto = new Objeto(connection);
            movimentacao = new Movimentacao(connection);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /*while (true){
            if (login()) break;
        }*/

        menu();
    }

    private static boolean login() {
        String nome = console.readLine("login: ");
        char[] senha = console.readPassword("senha: ");

        HashMap<String, Object> data = new HashMap<>();

        data.put("nome", nome);
        data.put("senha", String.valueOf(senha));

        HashMap<String, Object> usr = usuario.getOne(data);

        if (usr != null) {
            usuarioLogado = usr;
            console.printf("logado como "+nome+"\n");
            return true;
        }
        console.format("nome ou senha incorretos.\n");
        return false;
    }

    private static void menu() {
        printMenuOptions();
        while (true){
            String menuOption = readMenuOptions();
            if (! processOption(menuOption)) break;
        }
    }

    private static void printMenuOptions() {
        console.printf("#Almoxerifado app\n\n");
        console.printf("    Usuarios\n");
        console.printf("        |__  novo_______________[11]\n");
        console.printf("        |__  listar_____________[12]\n");
        console.printf("        |__  atualizar__________[13]\n");
        console.printf("        |__  excluir____________[14]\n\n");
        console.printf("    Categorias\n");
        console.printf("        |__  novo_______________[21]\n");
        console.printf("        |__  listar_____________[22]\n");
        console.printf("        |__  atualizar__________[23]\n");
        console.printf("        |__  excluir____________[24]\n\n");
        console.printf("    Objetos\n");
        console.printf("        |__  novo_______________[31]\n");
        console.printf("        |__  listar_____________[32]\n");
        console.printf("        |__  atualizar__________[33]\n");
        console.printf("        |__  excluir____________[34]\n\n");
        console.printf("    Movimentacoes\n");
        console.printf("        |__  Entradas\n");
        console.printf("        |       |__  novo_______[41]\n");
        console.printf("        |       |__  excluir____[42]\n\n");
        console.printf("        |__  Saidas\n");
        console.printf("                |__  novo_______[51]\n");
        console.printf("                |__  excluir____[52]\n\n\n");
        console.printf("    Opcoes\n");
        console.printf("        |__  mostrar menu_______[61]\n");
        console.printf("        |__  sair_______________[62]\n");
    }

    private static String readMenuOptions() {
        return console.readLine(": ");
    }

    private static void newUsuario() {
        try {
            console.format("novo usuario\n");
            HashMap<String, Object> data = new HashMap<>();
            data.put("nome", console.readLine("nome: "));
            data.put("senha", console.readLine("senha: "));
            usuario.create(data);
            console.format(data.get("nome")+" cadastrado!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listUsuario() {
        console.format("usuarios\n");
        System.out.println(usuario.getAll());
    }

    private static void updateUsuario() {
        console.format("atualizar usuario\n");
        try {
            HashMap<String, Object> usr;
            String id;
            while (true) {
                id = console.readLine("id:");
                HashMap<String, Object> filter = new HashMap<>();
                filter.put("id", id);
                usr = usuario.getOne(filter);

                System.out.println(usr);
                if (console.readLine("usuario correto?(s/n) ").equals("s")) break;   
            }
            HashMap<String, Object> data = new HashMap<>();
            for (String key : usr.keySet()) {
                String value = console.readLine(key+" ("+usr.get(key)+"):(enter para nao alterar) ");
                if (value.equals("")) {
                    data.put(key, usr.get(key));
                } else {
                    data.put(key, value);
                }
            }
            usuario.update(Integer.parseInt(id), data);
            console.format(data.get("nome")+" atualizado!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void deleteUsuario() {
        console.format("excluir usuario\n");
        try {
            HashMap<String, Object> usr;
            String id;
            while (true) {
                id = console.readLine("id:");
                HashMap<String, Object> filter = new HashMap<>();
                filter.put("id", id);
                usr = usuario.getOne(filter);
                System.out.println(usr);
                if (console.readLine("usuario correto?(s/n) ").equals("s")) break;   
            }
            String opt = console.readLine("realmente deseja excluir este usuario?(s/n)");
            if (opt.equals("s")) usuario.delete(Integer.valueOf(id));
            console.format(usr.get("nome")+" excluido!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void newCategoria() {
        
    }

    private static void listCategoria() {
        
    }

    private static void updateCategoria() {
        
    }
    
    private static void deleteCategoria() {
        
    }

    private static void newObjeto() {
        
    }

    private static void listObjeto() {
        
    }

    private static void updateObjeto() {
        
    }
    
    private static void deleteObjeto() {
        
    }

    private static boolean processOption(String option) {
        if (option.equals("11")) {
            newUsuario();
            return true;
        } else if (option.equals("12")) {
            listUsuario();
            return true;
        } else if (option.equals("13")) {
            updateUsuario();
            return true;
        } else if (option.equals("14")) {
            deleteUsuario();
            return true;
        } else if (option.equals("21")) {
            return true;
        } else if (option.equals("22")) {
            return true;
        } else if (option.equals("23")) {
            return true;
        } else if (option.equals("24")) {
            return true;
        } else if (option.equals("31")) {
            return true;
        } else if (option.equals("32")) {
            return true;
        } else if (option.equals("33")) {
            return true;
        } else if (option.equals("34")) {
            return true;
        } else if (option.equals("41")) {
            return true;
        } else if (option.equals("42")) {
            return true;
        } else if (option.equals("51")) {
            return true;
        } else if (option.equals("52")) {
            return true;
        } else if (option.equals("61")) {
            printMenuOptions();
            return true;
        } else if (option.equals("62")) {
            return false;
        }
        console.format("Opcao invalida!\n");
        return true;
    }
}