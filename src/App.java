import Model.Library;
import Controller.JournalController;
import View.MainMenu;

public class App {
    public static void main(String[] args) throws Exception {
        Library journal = new Library(); //Model (listas de midias)
        JournalController journalController = new JournalController(journal); //Controller (add, avaliar, etc)
        MainMenu menivis = new MainMenu(journalController); //View (print e prompt)
        menivis.showMenu();
    }
}
