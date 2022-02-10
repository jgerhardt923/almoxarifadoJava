package src.views;

import java.io.Console;
import java.util.HashMap;

import src.models.Model;

public class View {
    Model model;
    Console console;

    public View(Model model, Console console) {
        this.model = model;
        this.console = console;
    }

    public void create() {
        try {
            HashMap<String, Object> data = new HashMap<>();
            for (String key : model.getFields()) {
                data.put(key, console.readLine(key+": "));
            }
            model.create(data);
            console.format("criado!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void list() {
        console.format("itens\n");
        System.out.println(model.getAll());
    }
    
    public void update() {
        try {
            HashMap<String, Object> current;
            String id;
            while (true) {
                id = console.readLine("id:");
                HashMap<String, Object> filter = new HashMap<>();
                filter.put("id", id);
                current = model.getOne(filter);
                System.out.println(current);
                if (console.readLine("correto?(s/n) ").equals("s")) break;   
            }
            HashMap<String, Object> data = new HashMap<>();
            for (String key : model.getFields()) {
                String value = console.readLine(key+" ("+current.get(key)+"):(enter para nao alterar) ");
                if (value.equals("")) {
                    data.put(key, current.get(key));
                } else {
                    data.put(key, value);
                }
            }
            model.update(Integer.parseInt(id), data);
            console.format("atualizado!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete() {
        try {
            HashMap<String, Object> current;
            String id;
            while (true) {
                id = console.readLine("id:");
                HashMap<String, Object> filter = new HashMap<>();
                filter.put("id", id);
                current = model.getOne(filter);
                System.out.println(current);
                if (console.readLine("correto?(s/n) ").equals("s")) break;   
            }
            String opt = console.readLine("realmente deseja excluir este item?(s/n)");
            if (opt.equals("s")) model.delete(Integer.valueOf(id));
            console.format(" excluido!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
