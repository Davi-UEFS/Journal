package View;

import Controller.BookService;
import Controller.MovieService;
import Controller.SeriesService;
import Model.Genres;
import Model.Medias.Season;
import Model.Medias.Series;
import Model.Result.IResult;
import View.Prompts.*;

import java.time.Duration;
import java.util.Scanner;


public class RegisterMenu {
    private final Scanner scanner;
    private final BookService bookService;
    private final MovieService movieService;
    private final SeriesService seriesService;

    public RegisterMenu(BookService bookService, MovieService movieService,
                        SeriesService seriesService, Scanner scanner) {
        this.bookService = bookService;
        this.movieService = movieService;
        this.seriesService = seriesService;
        this.scanner = scanner;
    }

    public void show() {
        String title;
        int year;
        Genres genre;
        String originalTitle;
        String[] castBuffer;
        String[] whereToWatch;
        int seasonNumber;
        int episodeCount;

        IResult result;
        int option;

        do {
            System.out.println(Colors.purple + "--== MENU DE REGISTRO ==--" + Colors.rst);
            System.out.println("1 - Registrar livro");
            System.out.println("2 - Registrar filme");
            System.out.println("3 - Registrar série");
            System.out.println("4 - Registrar temporada");
            System.out.println(Colors.red + "0 - Voltar" + Colors.rst);
            option = Validate.validateInt(scanner);

            /*TODO: TRATAR EXCECOES NA VIEW?
                    DECLARAR VARIAVEIS FORA DOS CASES*/

            switch (option) {
                case 1: // Livro
                    title = AskInput.askForTitle(scanner);
                    year = AskInput.askForYear(scanner);
                    genre = AskInput.askForGenre(scanner);
                    String isbn = AskInput.askForISBN(scanner);
                    String author = AskInput.askForAuthor(scanner);
                    String publisher = AskInput.askForPublisher(scanner);
                    boolean owned = AskInput.askForOwned(scanner);

                    result = bookService.register(
                            title, year, genre, isbn, author, publisher, owned
                    );
                    System.out.println(result.getMessage());
                    break;

                case 2: // Filme
                    title = AskInput.askForTitle(scanner);
                    year = AskInput.askForYear(scanner);
                    genre = AskInput.askForGenre(scanner);
                    castBuffer = AskInput.askForCast(scanner);
                    Duration duration = AskInput.askForDuration(scanner);
                    String director = AskInput.askForDirector(scanner);
                    String script = AskInput.askForScript(scanner);
                    originalTitle = AskInput.askForOriginalTitle(scanner);
                    whereToWatch = AskInput.askForWhereToWatch(scanner);

                    result = movieService.register(
                            title, year, genre, castBuffer, duration, director,
                            script, originalTitle, whereToWatch
                    );
                    System.out.println(result.getMessage());
                    break;

                case 3: // Série
                    title = AskInput.askForTitle(scanner);
                    year = AskInput.askForYear(scanner);
                    genre = AskInput.askForGenre(scanner);
                    int yearOfEnding = AskInput.askForYearOfEnding(scanner);
                    castBuffer = AskInput.askForCast(scanner);
                    originalTitle = AskInput.askForOriginalTitle(scanner);
                    whereToWatch = AskInput.askForWhereToWatch(scanner);
                    seasonNumber = AskInput.askForSeasonNumber(scanner);
                    episodeCount = AskInput.askForEpisodeCount(scanner);

                    result = seriesService.register(
                            title, year, genre, yearOfEnding, castBuffer,
                            originalTitle, whereToWatch, seasonNumber, episodeCount
                    );
                    System.out.println(result.getMessage());

                    break;

                case 4:// Temporada
                    if(seriesService.getAllSeries().isEmpty())
                        System.out.println("Você não possui séries cadastradas");

                    else {
                        Series selectedSeries = AskInput.selectFromList(scanner, seriesService.getAllSeries());
                        seasonNumber = AskInput.askForSeasonNumber(scanner);
                        episodeCount = AskInput.askForEpisodeCount(scanner);

                        result = seriesService.registerSeason(selectedSeries, seasonNumber, episodeCount);

                        System.out.println(result.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(Colors.red + "Opção inválida " + Colors.rst);
                    break;

            }
        } while (option != 0);
    }
}
