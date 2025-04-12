package View;

import Controller.BookService;
import Controller.MovieService;
import Controller.SeriesService;
import Model.Genres;
import Model.Media.Book;
import Model.Media.Movie;
import Model.Media.Season;
import Model.Media.Series;

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
        String title;

        do{
            System.out.println(View.Prompts.Colors.green + "--== MENU DE DISPLAY ==--" + View.Prompts.Colors.rst);
            System.out.println("1 - Buscar livros");
            System.out.println("2 - Buscar filmes");
            System.out.println("3 - Buscar series");
            System.out.println(View.Prompts.Colors.red + "4 - Voltar" + View.Prompts.Colors.rst);

            option = View.Prompts.Validate.validateInt(scanner);

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

                case 4:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(View.Prompts.Colors.red + "Opção inválida" + View.Prompts.Colors.rst);
                    break;

            }
        }while (option!=4);
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
                    bookList = bookService.searchByTitle(title, bookService.allBooks());
                    printBookList(bookList);
                    break;

                case 2:

                    year = View.Prompts.AskInput.askForYear(scanner);
                    bookList = bookService.searchByYear(year, bookService.allBooks());
                    printBookList(bookList);
                    break;

                case 3:

                    genre = View.Prompts.AskInput.askForGenre(scanner);
                    bookList = bookService.searchByGenre(genre, bookService.allBooks());
                    printBookList(bookList);
                    break;

                case 4:

                    author = View.Prompts.AskInput.askForAuthor(scanner);
                    bookList = bookService.searchBookByAuthor(author);
                    printBookList(bookList);
                    break;

                case 5:
                    //isbn
                    isbn = View.Prompts.AskInput.askForISBN(scanner);
                    bookList = bookService.searchBookByIsbn(isbn);
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
                    movieList = movieService.searchByTitle(title, movieService.allMovies());
                    printMovieList(movieList);
                    break;

                case 2:
                    year = View.Prompts.AskInput.askForYear(scanner);
                    movieList = movieService.searchByYear(year, movieService.allMovies());
                    printMovieList(movieList);
                    break;

                case 3:
                    genre = View.Prompts.AskInput.askForGenre(scanner);
                    movieList = movieService.searchByGenre(genre, movieService.allMovies());
                    printMovieList(movieList);
                    break;

                case 4:
                    director = View.Prompts.AskInput.askForDirector(scanner);
                    movieList = movieService.searchByDirector(director);
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

    private void searchSeriesMiniMenu(){
        int option;
        String title;
        int year;
        Genres genre;
        String actor;
        List<Series> allSeries = seriesService.allSeries();
        List<Series> filteredSeriesList;

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
                    filteredSeriesList = seriesService.searchByTitle(title, allSeries);
                    printSeriesList(filteredSeriesList);
                    break;

                case 2:
                    year = View.Prompts.AskInput.askForYear(scanner);
                    filteredSeriesList = seriesService.searchByYear(year, allSeries);
                    printSeriesList(filteredSeriesList);
                    break;

                case 3:
                    genre = View.Prompts.AskInput.askForGenre(scanner);
                    filteredSeriesList = seriesService.searchByGenre(genre, allSeries);
                    printSeriesList(filteredSeriesList);
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
