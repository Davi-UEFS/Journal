import Controller.BookService;
import Controller.MovieService;
import Controller.SeriesService;
import Model.Library;
import View.Menus.MainMenu;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        //Model (listas de midias)
        Library journal = new Library();
        //Controllers (add, avaliar, etc)
        BookService bookService = new BookService(journal);
        MovieService movieService = new MovieService(journal);
        SeriesService seriesService = new SeriesService(journal);
        //View (print e prompt)
        MainMenu menivis = new MainMenu(bookService, movieService, seriesService, scanner);
        menivis.showMenu();
    }
}
