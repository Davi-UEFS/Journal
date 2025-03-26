package Menus;

import Journal.JournalController;
import Media.Book;
import Media.Movie;
import Media.Season;
import Media.Series;

import java.util.ArrayList;
import java.util.Scanner;

public class DisplayMenu {
    private Scanner scanner;
    private JournalController journalController;

    public DisplayMenu(JournalController journalController, Scanner scanner) {
        this.journalController = journalController;
        
        this.scanner = scanner;
    }


    private void displayMiniMenu() {

        int option;

        do{
            System.out.println("1 - Ver avaliacoes");
            System.out.println("2 - Ver livros cadastrados");
            System.out.println("3 - Ver filmes cadastrados");
            System.out.println("4 - Ver series cadastradas"); //TODO GRAM
            System.out.println("5 - Voltar");

            option = Inputs.Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    System.out.print("Obra desejada: ");
                    String title = Inputs.Validate.validateString(scanner);

                    System.out.println(journalController.readReview(title));
                    //TODO JUNTAR METODOS?
                    System.out.println(journalController.showRating(title));
                    break;

                case 2:
                    printAllBooks();
                    break;

                case 3:
                    printAllMovies();
                    break;

                case 4:
                    printAllSeries();
                    break;

                default:
                    System.out.println("Opcao invalida"); //TODO GRAM
                    break;

            }
        }while (option!=5);
    }

    private void printAllBooks(){
        ArrayList<Book> bookList = journalController.allBooks();
        for(Book book: bookList)
            System.out.printf("Titulo: %s (%d) \n ", book.getTitle(), book.getYear());

    }

    private void printAllMovies(){
        ArrayList<Movie> movieList = journalController.allMovies();

        for(Movie movie: movieList)
            System.out.printf("Titulo: %s (%d) \n", movie.getTitle(), movie.getYear());

    }

    private void printAllSeries(){
        ArrayList<Series> seriesList = journalController.allSeries();

        for(Series series: seriesList){
            System.out.printf("Titulo: %s (%d) \n", series.getTitle(), series.getYear());
            System.out.print("\t");
            for(Season season: series.getSeasons())
                System.out.printf("Temporada: %d \n", season.getSeasonNumber());

        }
    }
}
