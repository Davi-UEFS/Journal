import Journal.JournalModel;
import Journal.JournalController;
import Menus.MainMenu;

public class App {
    public static void main(String[] args) throws Exception {
        JournalModel journal = new JournalModel(); //Model (listas de midias)
        JournalController journalController = new JournalController(journal); //Controller (add, avaliar, etc)
        MainMenu menivis = new MainMenu(journalController); //View (print e prompt)
        menivis.showMenu();

    }
}
