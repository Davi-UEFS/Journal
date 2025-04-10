package View;

import Controller.JournalController;
import Model.Media.*;

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
            System.out.println(View.Prompts.Colors.green + "--== MENU DE DISPLAY ==--" + View.Prompts.Colors.rst);
            System.out.println("1 - Ver avaliações");
            System.out.println("2 - Ver livros cadastrados");
            System.out.println("3 - Ver filmes cadastrados");
            System.out.println("4 - Ver series cadastradas");
            System.out.println("5 - Buscar livros");
            System.out.println("6 - Buscar filmes");
            System.out.println("7 - Buscar series");
            System.out.println(View.Prompts.Colors.red + "8 - Voltar" + View.Prompts.Colors.rst);

            option = View.Prompts.Validate.validateInt(scanner);

            switch (option) {

                case 1:

                    title = View.Prompts.AskInput.askForTitle(scanner);

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
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    List<Book> bookList = journalController.searchBook(title);
                    printBookList(bookList);
                    break;

                case 6:
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    List<Movie> movieList = journalController.searchMovie(title);
                    printMovieList(movieList);
                    break;

                case 7:
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    List<Series> seriesList = journalController.searchSeries(title);
                    printSeriesList(seriesList);
                    break;

                case 8:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(View.Prompts.Colors.red + "Opção inválida" + View.Prompts.Colors.rst);
                    break;

            }
        }while (option!=8);
    }

    private void printBookList(List<Book> bookList) {
        for(Book book: bookList)
            System.out.println(book.toString());
    }

    private void printMovieList(List<Movie> movieList) {
        for(Movie movie: movieList)
            System.out.println(movie.toString());

    }

    private void printSeriesList(List<Series> seriesList) {
        for(Series series: seriesList){
            System.out.println(series.toString());
            for(Season season: series.getSeasons()) {
                System.out.print("\t");
                System.out.println(season.toString());
            }

        }
    }

    private void printMediaList(ArrayList<Media> mediaList){
        for(Media media: mediaList){
            System.out.printf("Titulo: %s (%d) \n", media.getTitle(), media.getYear());
        }
    }
}
