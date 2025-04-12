package View;

import Controller.BookService;
import Controller.MovieService;
import Controller.SeriesService;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner;  //TODO: FINAL?
    private final BookService bookService;
    private final MovieService movieService;
    private final SeriesService seriesService;
    private final DisplayMenu displayMenu;
    private final RegisterMenu registerMenu;
    private final RateMenu rateMenu;
    
    public MainMenu(BookService bookService, MovieService movieService,
                    SeriesService seriesService, Scanner scanner) {
        this.bookService = bookService;
        this.movieService = movieService;
        this.seriesService = seriesService;
        this.displayMenu = new DisplayMenu(bookService, movieService, seriesService, scanner);
        this.rateMenu = new RateMenu(bookService, movieService, seriesService, scanner);
        this.registerMenu = new RegisterMenu(bookService, movieService, seriesService, scanner);
        this.scanner = scanner;
    }

    public void showMenu(){
        int option;

        do{
            System.out.println(View.Prompts.Colors.cyan + "--== DIÁRIO CULTURAL ==--" + View.Prompts.Colors.rst);
            System.out.println("1 - Registrar ");
            System.out.println("2 - Avaliar ");
            System.out.println("3 - Ver");
            System.out.println(View.Prompts.Colors.red + "4 - Encerrar " + View.Prompts.Colors.rst);

            option = View.Prompts.Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    registerMenu.show();

                    break;

                case 2:
                    rateMenu.show();
                    break;

                case 3:
                    displayMenu.show();
                    break;

                case 4:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println(View.Prompts.Colors.red + "Opção inválida " + View.Prompts.Colors.rst);
                    break;
            }
            
        }while(option != 4);
        scanner.close();
    }


}
