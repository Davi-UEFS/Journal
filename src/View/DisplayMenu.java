package View;

import Controller.JournalController;
import Model.Media.*;
import Model.Genres;

import java.util.List;
import java.util.Scanner;

public class DisplayMenu {
    private final Scanner scanner;
    private final JournalController journalController;

    public DisplayMenu(JournalController journalController, Scanner scanner) {
        this.journalController = journalController;
        
        this.scanner = scanner;
    }

    private void sortByMiniMenu(){
        int option;

        do{
            System.out.println(View.Prompts.Colors.green + "--== MENU DE DISPLAY ==--" + View.Prompts.Colors.rst);
            System.out.println("1 - Por avaliação (crescente) ");
            System.out.println("2 - Por avaliação (decrescente)");
            System.out.println("3 - Por gênero (crescente)");
            System.out.println("4 - Por gênero (decrescente)");
            System.out.println("5 - Por ano de lançamento (crescente)");
            System.out.println("6 - Por ano de lançamento (decrescente)");
            System.out.println(View.Prompts.Colors.red + "7 - Voltar" + View.Prompts.Colors.rst);

            option = View.Prompts.Validate.validateInt(scanner);

            switch (option) {

                case 1:

                    System.out.println(journalController.allBooks());

                    break;

                case 2:
                    System.out.println(journalController.sortDescending(journalController.allBooks()));

                    break;

                case 3:
                    System.out.println(journalController.booksByGenreTextAscending());
                    break;

                case 4:
                    System.out.println(journalController.booksByGenreTextDescending());
                    break;

                case 5:

                    break;

                case 6:

                    break;

                case 7:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(View.Prompts.Colors.red + "Opção inválida" + View.Prompts.Colors.rst);
                    break;

            }
        }while (option!=7);
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
                    searchBookMiniMenu();
                    break;

                case 6:
                    searchMovieMiniMenu();
                    break;

                case 7:
                    searchSeriesMiniMenu();
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

    private void searchBookMiniMenu(){
        int option;
        String title;
        int year;
        Genres genre;
        String author;
        String isbn;
        List<Book> bookList;
        do {
            System.out.println(View.Prompts.Colors.green + "--== BUSCAR LIVRO ==--" + View.Prompts.Colors.rst);
            System.out.println("1 - Buscar por título");
            System.out.println("2 - Buscar por ano");
            System.out.println("3 - Buscar por gênero");
            System.out.println("4 - Buscar por autor");
            System.out.println("5 - Buscar por ISBN");
            System.out.println(View.Prompts.Colors.red + "6 - Voltar" + View.Prompts.Colors.rst);

            option = View.Prompts.Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    //title
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    bookList = journalController.searchBookByTitle(title);
                    printBookList(bookList);
                    break;

                case 2:
                    //ano
                    year = View.Prompts.AskInput.askForYear(scanner);
                    bookList = journalController.searchBookByYear(year);
                    printBookList(bookList);
                    break;

                case 3:
                    //genre
                    genre = View.Prompts.AskInput.askForGenre(scanner);
                    bookList = journalController.searchBookByGenre(genre);
                    printBookList(bookList);
                    break;

                case 4:
                    //author
                    author = View.Prompts.AskInput.askForAuthor(scanner);
                    bookList = journalController.searchBookByAuthor(author);
                    printBookList(bookList);
                    break;

                case 5:
                    //isbn
                    isbn = View.Prompts.AskInput.askForISBN(scanner);
                    bookList = journalController.searchBookByIsbn(isbn);
                    printBookList(bookList);
                    break;

                case 6:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(View.Prompts.Colors.red + "Opção inválida" + View.Prompts.Colors.rst);
                    break;
            }
        } while (option!=6);
    }

    private void searchMovieMiniMenu(){
        int option;
        String title;
        int year;
        Genres genre;
        String director;
        String actor;
        List<Movie> movieList;
        do {
            System.out.println(View.Prompts.Colors.green + "--== BUSCAR FILME ==--" + View.Prompts.Colors.rst);
            System.out.println("1 - Buscar por título");
            System.out.println("2 - Buscar por ano");
            System.out.println("3 - Buscar por gênero");
            System.out.println("4 - Buscar por diretor");
            System.out.println("5 - Buscar por ator no elenco");
            System.out.println(View.Prompts.Colors.red + "6 - Voltar" + View.Prompts.Colors.rst);

            option = View.Prompts.Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    movieList = journalController.searchMovieByTitle(title);
                    printMovieList(movieList);
                    break;

                case 2:
                    year = View.Prompts.AskInput.askForYear(scanner);
                    movieList = journalController.searchMovieByYear(year);
                    printMovieList(movieList);
                    break;

                case 3:
                    genre = View.Prompts.AskInput.askForGenre(scanner);
                    movieList = journalController.searchMovieByGenre(genre);
                    printMovieList(movieList);
                    break;

                case 4:
                    director = View.Prompts.AskInput.askForDirector(scanner);
                    movieList = journalController.searchMovieByDirector(director);
                    printMovieList(movieList);
                    break;

                case 5:
                    // TODO: FAZER BUSCA POR ATOR
                    break;

                case 6:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(View.Prompts.Colors.red + "Opção inválida " + View.Prompts.Colors.rst);
                    break;
            }
        } while(option != 6);
    }

    public void searchSeriesMiniMenu(){
        int option;
        String title;
        int year;
        Genres genre;
        String actor;
        List<Series> seriesList;
        do {
            System.out.println(View.Prompts.Colors.green + "--== BUSCAR SÉRIE ==--" + View.Prompts.Colors.rst);
            System.out.println("1 - Buscar por título");
            System.out.println("2 - Buscar por ano de lançamento");
            System.out.println("3 - Buscar por gênero");
            System.out.println("4 - Buscar por ator no elenco");
            System.out.println(View.Prompts.Colors.red + "5 - Voltar" + View.Prompts.Colors.rst);

            option = View.Prompts.Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    seriesList = journalController.searchSeriesByTitle(title);
                    printSeriesList(seriesList);
                    break;

                case 2:
                    year = View.Prompts.AskInput.askForYear(scanner);
                    seriesList = journalController.searchSeriesByYear(year);
                    printSeriesList(seriesList);
                    break;

                case 3:
                    genre = View.Prompts.AskInput.askForGenre(scanner);
                    seriesList = journalController.searchSeriesByGenre(genre);
                    printSeriesList(seriesList);
                    break;

                case 4:
                    //TODO: FAZER BUSCA POR ATOR
                    break;

                case 5:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(View.Prompts.Colors.red + "Opção inválida " + View.Prompts.Colors.rst);
                    break;
            }
        } while(option != 5);
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

}
