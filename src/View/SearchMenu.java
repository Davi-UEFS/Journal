package View;

import Controller.BookService;
import Controller.MovieService;
import Controller.SeriesService;
import Model.Genres;
import Model.Medias.*;
import View.Prompts.Colors;

import View.Prompts.*;

import java.util.List;
import java.util.Scanner;

public class SearchMenu {
    private final Scanner scanner;
    private final BookService bookService;
    private final MovieService movieService;
    private final SeriesService seriesService;

    public SearchMenu(BookService bookService, MovieService movieService,
                      SeriesService seriesService, Scanner scanner) {
        this.bookService = bookService;
        this.movieService = movieService;
        this.seriesService = seriesService;
        this.scanner = scanner;
    }

    public void show() {

        int option;

        do{
            System.out.println(Colors.green + "--== MENU DE DISPLAY ==--" + Colors.rst);
            System.out.println("1 - Buscar livros");
            System.out.println("2 - Buscar filmes");
            System.out.println("3 - Buscar series");
            System.out.println(Colors.red + "0 - Voltar" + Colors.rst);

            option = Validate.validateInt(scanner);

            switch (option) {

                case 1:
                    searchBookMiniMenu();
                    break;

                case 2:
                    searchMovieMiniMenu();
                    break;

                case 3:
                    searchSeriesMiniMenu();
                    break;

                case 0:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(Colors.red + "Opção inválida" + Colors.rst);
                    break;

            }
        }while (option!=0);
    }

    private void searchBookMiniMenu(){
        int option;
        String title;
        int year;
        Genres genre;
        String author;
        String isbn;
        List<Book> filteredBookList;
        List<Book> allBooks = bookService.getAllBooks();

        do {
            System.out.println(Colors.green + "--== BUSCAR LIVRO ==--" + Colors.rst);
            System.out.println("1 - Buscar por título");
            System.out.println("2 - Buscar por ano");
            System.out.println("3 - Buscar por gênero");
            System.out.println("4 - Buscar por autor");
            System.out.println("5 - Buscar por ISBN");
            System.out.println(Colors.red + "0 - Voltar" + Colors.rst);

            option = Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    //TODO: EVITAR TER QUE CRIAR ALLBOOKS? VALE PARA FILME E SERIE
                    title = AskInput.askForTitle(scanner);
                    filteredBookList = bookService.searchByTitle(title, allBooks);
                    printBookList(filteredBookList);
                    break;

                case 2:

                    year = AskInput.askForYear(scanner);
                    filteredBookList = bookService.searchByYear(year, allBooks);
                    printBookList(filteredBookList);
                    break;

                case 3:

                    genre = AskInput.askForGenre(scanner);
                    filteredBookList = bookService.searchByGenre(genre, allBooks);
                    printBookList(filteredBookList);
                    break;

                case 4:

                    author = AskInput.askForAuthor(scanner);
                    filteredBookList = bookService.searchBookByAuthor(author);
                    printBookList(filteredBookList);
                    break;

                case 5:
                    //isbn
                    isbn = AskInput.askForISBN(scanner);
                    filteredBookList = bookService.searchBookByIsbn(isbn);
                    printBookList(filteredBookList);
                    break;

                case 0:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(Colors.red + "Opção inválida" + Colors.rst);
                    break;
            }
        } while (option!=0);
    }

    private void searchMovieMiniMenu(){
        int option;
        String title;
        int year;
        Genres genre;
        String director;
        String actor;
        List<Movie> filteredMovieList;
        List<Movie> allMovies = movieService.getAllMovies();

        do {
            System.out.println(Colors.green + "--== BUSCAR FILME ==--" + Colors.rst);
            System.out.println("1 - Buscar por título");
            System.out.println("2 - Buscar por ano");
            System.out.println("3 - Buscar por gênero");
            System.out.println("4 - Buscar por diretor");
            System.out.println("5 - Buscar por ator no elenco");
            System.out.println(Colors.red + "0 - Voltar" + Colors.rst);

            option = Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    title = AskInput.askForTitle(scanner);
                    filteredMovieList = movieService.searchByTitle(title, allMovies);
                    printMovieList(filteredMovieList);
                    break;

                case 2:
                    year = AskInput.askForYear(scanner);
                    filteredMovieList = movieService.searchByYear(year, allMovies);
                    printMovieList(filteredMovieList);
                    break;

                case 3:
                    genre = AskInput.askForGenre(scanner);
                    filteredMovieList = movieService.searchByGenre(genre, allMovies);
                    printMovieList(filteredMovieList);
                    break;

                case 4:
                    director = AskInput.askForDirector(scanner);
                    filteredMovieList = movieService.searchByDirector(director);
                    printMovieList(filteredMovieList);
                    break;

                case 5:
                    actor = AskInput.askForAuthor(scanner);
                    filteredMovieList = movieService.searchByActor(actor);
                    printMovieList(filteredMovieList);
                    break;

                case 0:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(Colors.red + "Opção inválida " + Colors.rst);
                    break;
            }
        } while(option != 0);
    }

    private void searchSeriesMiniMenu(){
        int option;
        String title;
        int year;
        Genres genre;
        String actor;
        List<Series> allSeries = seriesService.getAllSeries();
        List<Series> filteredSeriesList;

        do {
            System.out.println(Colors.green + "--== BUSCAR SÉRIE ==--" + Colors.rst);
            System.out.println("1 - Buscar por título");
            System.out.println("2 - Buscar por ano de lançamento");
            System.out.println("3 - Buscar por gênero");
            System.out.println("4 - Buscar por ator no elenco");
            System.out.println(Colors.red + "0 - Voltar" + Colors.rst);

            option = Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    title = AskInput.askForTitle(scanner);
                    filteredSeriesList = seriesService.searchByTitle(title, allSeries);
                    printSeriesList(filteredSeriesList);
                    break;

                case 2:
                    year = AskInput.askForYear(scanner);
                    filteredSeriesList = seriesService.searchByYear(year, allSeries);
                    printSeriesList(filteredSeriesList);
                    break;

                case 3:
                    genre = AskInput.askForGenre(scanner);
                    filteredSeriesList = seriesService.searchByGenre(genre, allSeries);
                    printSeriesList(filteredSeriesList);
                    break;

                case 4:
                    actor = AskInput.askForAuthor(scanner);
                    filteredSeriesList = seriesService.searchByActor(actor);
                    printSeriesList(filteredSeriesList);
                    break;

                case 0:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(Colors.red + "Opção inválida " + Colors.rst);
                    break;
            }
        } while(option != 0);
    }

    private void printBookList(List<Book> bookList) {
        for(Book book: bookList)
            System.out.println(book);
    }

    private void printMovieList(List<Movie> movieList) {
        for(Movie movie: movieList)
            System.out.println(movie);

    }

    //TODO: PRINT DE TEMPORADAS NO TOSTRING DE SERIE?
    private void printSeriesList(List<Series> seriesList) {
        for(Series series: seriesList){
            System.out.println(series);
            for(Season season: series.getSeasons()) {
                System.out.print("\t");
                System.out.println(season);
            }

        }
    }
}
