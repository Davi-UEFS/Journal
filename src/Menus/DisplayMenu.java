package Menus;

import Journal.JournalController;
import Media.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisplayMenu {
    private Scanner scanner;
    private JournalController journalController;

    public DisplayMenu(JournalController journalController, Scanner scanner) {
        this.journalController = journalController;
        
        this.scanner = scanner;
    }


    public void displayMiniMenu() {

        int option;
        String title;

        do{
            System.out.println(Inputs.Colors.green + "--== MENU DE DISPLAY ==--" + Inputs.Colors.rst);
            System.out.println("1 - Ver avaliações");
            System.out.println("2 - Ver livros cadastrados");
            System.out.println("3 - Ver filmes cadastrados");
            System.out.println("4 - Ver series cadastradas");
            System.out.println("5 - Buscar livros");
            System.out.println("6 - Buscar filmes");
            System.out.println("7 - Buscar series");
            System.out.println(Inputs.Colors.red + "8 - Voltar" + Inputs.Colors.rst);

            option = Inputs.Validate.validateInt(scanner);

            switch (option) {

                case 1:

                    title = Inputs.AskInput.askForTitle(scanner);

                    System.out.println(journalController.readReview(title));
                    //TODO JUNTAR METODOS?
                    System.out.println(journalController.showRating(title));
                    break;

                case 2:
                    printBookList(journalController.allBooks());
                    break;

                case 3:
                    printMovieList(journalController.allMovies());
                    break;

                case 4:
                    printSeriesList(journalController.allSeries());
                    break;

                case 5:
                    title = Inputs.AskInput.askForTitle(scanner);
                    List<Book> bookList = journalController.searchBook(title);
                    printBookList(bookList);
                    break;

                case 6:
                    title = Inputs.AskInput.askForTitle(scanner);
                    List<Movie> movieList = journalController.searchMovie(title);
                    printMovieList(movieList);
                    break;

                case 7:
                    title = Inputs.AskInput.askForTitle(scanner);
                    List<Series> seriesList = journalController.searchSeries(title);
                    printSeriesList(seriesList);
                    break;

                default:
                    System.out.println(Inputs.Colors.red + "Opção inválida" + Inputs.Colors.rst);
                    break;

            }
        }while (option!=8);
    }

    private void printBookList(List<Book> bookList) {
        for(Book book: bookList)
            System.out.printf("Titulo: %s (%d)\n", book.getTitle(), book.getYear());

    }

    private void printMovieList(List<Movie> movieList) {
        for(Movie movie: movieList)
            System.out.printf("Titulo: %s (%d)\n", movie.getTitle(), movie.getYear());

    }

    private void printSeriesList(List<Series> seriesList) {
        for(Series series: seriesList){
            System.out.printf("Titulo: %s (%d-%d)\n", series.getTitle(), series.getYear(), series.getYearOfEnding());
            for(Season season: series.getSeasons()) {
                System.out.print("\t");
                System.out.printf("Temporada: %d \n", season.getSeasonNumber());
            }

        }
    }

    private void printMediaList(ArrayList<Media> mediaList){
        for(Media media: mediaList){
            System.out.printf("Titulo: %s (%d) \n", media.getTitle(), media.getYear());
        }
    }
}
