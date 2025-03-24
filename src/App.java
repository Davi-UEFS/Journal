import Journal.*;
import Menus.MainMenu;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Bem vindo(a)! ");
        Journal journal = new Journal(); //Model (listas de midias)
        JournalController journalController = new JournalController(journal); //Controller (add, avaliar, etc)
        MainMenu menivis = new MainMenu(journalController); //View (print e prompt)
        menivis.showMenu();

    }
}
