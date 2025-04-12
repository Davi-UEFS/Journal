package View;

import Controller.BookService;
import Controller.MovieService;
import Controller.SeriesService;

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
        String title, review;
        int seasonNumber;
        double rating;

        do {
            System.out.println(View.Prompts.Colors.blue + "--== MENU DE AVALIAÇÃO ==--" + View.Prompts.Colors.rst);
            System.out.println("1 - Avaliar livro");
            System.out.println("2 - Avaliar filme");
            System.out.println("3 - Avaliar temporada");
            System.out.println("4 - Escrever review (livro)");
            System.out.println("5 - Escrever review (filme)");
            System.out.println("6 - Escrever review (temporada)");

            System.out.println(View.Prompts.Colors.red + "7 - Voltar" + View.Prompts.Colors.rst);

            option = View.Prompts.Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    rating = View.Prompts.AskInput.askForRate(scanner);

                    System.out.println(bookService.rate(title, rating));

                    break;
                case 2:
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    rating = View.Prompts.AskInput.askForRate(scanner);

                    System.out.println(movieService.rate(title, rating));

                    break;

                case 3:
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    rating = View.Prompts.AskInput.askForRate(scanner);

                    System.out.println(seriesService.rate(title, rating));

                    break;

                case 4:
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    review = View.Prompts.AskInput.askForReview(scanner);

                    System.out.println(bookService.writeReview(title, review));
                    break;

                case 5:
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    review = View.Prompts.AskInput.askForReview(scanner);

                    System.out.println(movieService.writeReview(title, review));
                    break;

                case 6:
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    seasonNumber = View.Prompts.AskInput.askForSeasonNumber(scanner);
                    review = View.Prompts.AskInput.askForReview(scanner);

                    System.out.println(seriesService.writeReview(title, seasonNumber, review));
                    break;

                case 7:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(View.Prompts.Colors.red + "Opção inválida " + View.Prompts.Colors.rst);
                    break;
            }
        } while (option != 5);
    }
}
