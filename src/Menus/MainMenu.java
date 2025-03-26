package Menus;

import Journal.JournalController;
import Media.Book;
import Media.Movie;
import Media.Season;
import Media.Series;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {

    private Scanner scanner = new Scanner(System.in);  //TODO: FINAL?
    private JournalController journalController;
    private DisplayMenu displayMenu;
    private RegisterMenu registerMenu;
    private RateMenu rateMenu;
    
    public MainMenu(JournalController journalController) {
        this.journalController = journalController;
        this.displayMenu = new DisplayMenu(journalController, scanner);
        this.rateMenu = new RateMenu(journalController, scanner);
        this.registerMenu = new RegisterMenu(journalController, scanner);
    }

    public void showMenu(){
        int option;

        do{
            System.out.println("1 - Registrar ");
            System.out.println("2 - Avaliar ");
            System.out.println("3 - Ver");
            System.out.println("4 - Encerrar ");

            option = Inputs.Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    registerMenu.registerMiniMenu(journalController);

                    break;

                case 2:
                    rateMenu.rateMiniMenu();
                    break;

                case 3:
                    viewMiniMenu();
                    break;

                case 4:
                    //Instructions here
                    break;
                
                default:
                    break;
            }
            
        }while(option != 4);
        scanner.close();
    }

    private void viewMiniMenu(){
        System.out.println("1 - Ver avaliacoes");
        System.out.println("2 - Ver livros cadastrados");
        System.out.println("3 - Ver filmes cadastrados");
        System.out.println("4 - Ver series cadastradas"); //TODO GRAM

        int option = Inputs.Validate.validateInt(scanner);

        switch (option){
            case 1:
                System.out.print("Obra desejada: ");
                String name = Inputs.Validate.validateString(scanner);
                System.out.println(journalController.readReview(name));
                //TODO JUNTAR METODOS
                System.out.println(journalController.showRating(name));
                break;

            case 2:
                /*printAllBooks();
                printAllMovies();
                printAllSeries();*/
                System.out.println("Coming soon!");
                break;

            default:
                System.out.println("Opcao invalida"); //TODO GRAM
                break;
        }

    }


}
