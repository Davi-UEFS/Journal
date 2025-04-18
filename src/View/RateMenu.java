package View;

import Controller.BookService;
import Controller.MovieService;
import Controller.SeriesService;
import Model.Medias.*;
import Model.Months;
import Model.Result.IResult;
import View.Prompts.*;

import java.util.Scanner;

public class RateMenu {
    private final Scanner scanner;
    private final BookService bookService;
    private final MovieService movieService;
    private final SeriesService seriesService;

    public RateMenu(BookService bookService, MovieService movieService,
                        SeriesService seriesService, Scanner scanner) {
        this.bookService = bookService;
        this.movieService = movieService;
        this.seriesService = seriesService;
        this.scanner = scanner;
    }

    public void show() {

        int option;
        String review;
        int seasonNumber;
        double rating;
        Book selectedBook;
        Movie selectedMovie;
        Series selectedSeries;
        IResult result;

        do {
            System.out.println(Colors.blue + "--== MENU DE AVALIAÇÃO ==--" + Colors.rst);
            System.out.println("1 - Avaliar livro");
            System.out.println("2 - Avaliar filme");
            System.out.println("3 - Avaliar temporada");
            System.out.println("4 - Escrever review (livro)");
            System.out.println("5 - Escrever review (filme)");
            System.out.println("6 - Escrever review (temporada)");
            System.out.println("7 - Marcar como visto (livro)");
            System.out.println("8 - Marcar como visto (filme)");
            System.out.println("9 - Marcar como visto (temporada)");

            System.out.println(Colors.red + "0 - Voltar" + Colors.rst);

            option = Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    if(bookService.getAllBooks().isEmpty())
                        System.out.println("Você não possui livros cadastrados.");
                    else {
                        selectedBook = AskInput.selectFromList(scanner, bookService.getAllBooks());
                        rating = AskInput.askForRate(scanner);
                        result = bookService.rate(selectedBook, rating);
                        System.out.println(result.getMessage());
                    }

                    break;
                case 2:
                    if(movieService.getAllMovies().isEmpty())
                        System.out.println("Você não possui filmes cadastrados.");
                    else {
                        selectedMovie = AskInput.selectFromList(scanner, movieService.getAllMovies());
                        rating = AskInput.askForRate(scanner);

                        result = movieService.rate(selectedMovie, rating);
                        System.out.println(result.getMessage());
                    }
                    break;

                case 3:
                    if(seriesService.getAllSeries().isEmpty())
                        System.out.println("Você não possui séries cadastradas.");
                    else {
                        selectedSeries = AskInput.selectFromList(scanner, seriesService.getAllSeries());
                        rating = AskInput.askForRate(scanner);
                        seasonNumber = AskInput.askForSeasonNumber(scanner);

                        result = seriesService.rateSeason(selectedSeries, seasonNumber, rating);
                        System.out.println(result.getMessage());
                    }
                    break;

                case 4:
                    if(bookService.getAllBooks().isEmpty())
                        System.out.println("Você não possui livros cadastrados.");
                    else {
                        selectedBook = AskInput.selectFromList(scanner, bookService.getAllBooks());
                        review = AskInput.askForReview(scanner);

                        result = bookService.writeReview(selectedBook, review);
                        System.out.println(result.getMessage());
                    }
                    break;

                case 5:
                    if(movieService.getAllMovies().isEmpty())
                        System.out.println("Você não possui filmes cadastrados.");
                    else {
                        selectedMovie = AskInput.selectFromList(scanner, movieService.getAllMovies());
                        review = AskInput.askForReview(scanner);

                        result = movieService.writeReview(selectedMovie, review);
                        System.out.println(result.getMessage());
                    }
                    break;

                case 6:
                    if(seriesService.getAllSeries().isEmpty())
                        System.out.println("Você não possui séries cadastradas.");
                    else {
                        selectedSeries = AskInput.selectFromList(scanner, seriesService.getAllSeries());
                        seasonNumber = AskInput.askForSeasonNumber(scanner);
                        review = AskInput.askForReview(scanner);

                        result = seriesService.writeReviewSeason(selectedSeries, seasonNumber, review);
                        System.out.println(result.getMessage());
                    }
                    break;

                case 7:
                    if(bookService.getAllBooks().isEmpty())
                        System.out.println("Você não possui livros cadastrados.");
                    else{
                        selectedBook = AskInput.selectFromList(scanner, bookService.getAllBooks());

                        int ano = AskInput.askForSeenYear(scanner);
                        Months mes = AskInput.askForSeenMonth(scanner);

                        result = bookService.markAsSeen(selectedBook, ano, mes);
                        System.out.println(result.getMessage());
                    }
                    break;

                case 8:
                    if(movieService.getAllMovies().isEmpty())
                        System.out.println("Você não possui filmes cadastrados.");
                    else {
                        selectedMovie = AskInput.selectFromList(scanner, movieService.getAllMovies());

                        int ano = AskInput.askForSeenYear(scanner);
                        Months mes = AskInput.askForSeenMonth(scanner);

                        result = movieService.markAsSeen(selectedMovie, ano, mes);
                        System.out.println(result.getMessage());
                    }
                    break;

                case 9:
                    if(seriesService.getAllSeries().isEmpty())
                        System.out.println("Você não possui séries cadastradas.");
                    else {
                        selectedSeries = AskInput.selectFromList(scanner, seriesService.getAllSeries());
                        seasonNumber = AskInput.askForSeasonNumber(scanner);

                        result = seriesService.markAsSeenSeason(selectedSeries, seasonNumber);
                        System.out.println(result.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(Colors.red + "Opção inválida." + Colors.rst);
                    break;
            }
        } while (option != 0);
    }
}
