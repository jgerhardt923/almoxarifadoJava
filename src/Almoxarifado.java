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

    private static boolean processOption(String option) {
        if (option.equals("11")) {
            return true;
        } else if (option.equals("12")) {
            return true;
        } else if (option.equals("13")) {
            return true;
        } else if (option.equals("14")) {
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
            return true;
        } else if (option.equals("62")) {
            return false;
        }
        console.format("Opcao invalida!\n");
        return true;
    }
}