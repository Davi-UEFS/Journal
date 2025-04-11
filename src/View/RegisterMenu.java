package View;

import Model.Genres;
import View.Prompts.*;
import Controller.JournalController;

import java.time.Duration;
import java.util.Scanner;


public class RegisterMenu {
    private Scanner scanner;
    private JournalController journalController;

    public RegisterMenu(JournalController journalController, Scanner scanner) {
        this.journalController = journalController;
        this.scanner = scanner;
    }

    public void registerMiniMenu(JournalController journalController) {
        String title;
        int year;
        Genres genre;
        String originalTitle;
        String[] castBuffer;
        String[] whereToWatch;

        int option;

        do {
            System.out.println(View.Prompts.Colors.purple + "--== MENU DE REGISTRO ==--" + View.Prompts.Colors.rst);
            System.out.println("1 - Registrar livro");
            System.out.println("2 - Registrar filme");
            System.out.println("3 - Registrar série");
            System.out.println(View.Prompts.Colors.red + "4 - Voltar" + View.Prompts.Colors.rst);
            option = View.Prompts.Validate.validateInt(scanner);

            /*TODO: TRATAR EXCECOES NA VIEW?
                    DECLARAR VARIAVEIS FORA DOS CASES*/

            switch (option) {
                case 1: // Livro
                    title = AskInput.askForTitle(scanner);
                    year = AskInput.askForYear(scanner);
                    genre = AskInput.askForGenre(scanner);
                    String isbn = AskInput.askForISBN(scanner); // Variável exclusiva do case 1
                    String author = AskInput.askForAuthor(scanner);
                    String publisher = AskInput.askForPublisher(scanner);
                    boolean owned = AskInput.askForOwned(scanner);

                    System.out.println(journalController.register(
                            title, year, genre, isbn, author, publisher, owned
                    ));
                    break;

                case 2: // Filme
                    title = AskInput.askForTitle(scanner);
                    year = AskInput.askForYear(scanner);
                    genre = AskInput.askForGenre(scanner);
                    castBuffer = AskInput.askForCast(scanner);
                    Duration duration = AskInput.askForDuration(scanner); // Exclusivo do case 2
                    String director = AskInput.askForDirector(scanner);
                    String script = AskInput.askForScript(scanner);
                    originalTitle = AskInput.askForOriginalTitle(scanner);
                    whereToWatch = AskInput.askForWhereToWatch(scanner);

                    System.out.println(journalController.register(
                            title, year, genre, castBuffer, duration, director,
                            script, originalTitle, whereToWatch
                    ));
                    break;

                case 3: // Série
                    title = AskInput.askForTitle(scanner);
                    year = AskInput.askForYear(scanner);
                    genre = AskInput.askForGenre(scanner);
                    int yearOfEnding = AskInput.askForYearOfEnding(scanner); // Exclusivo do case 3
                    castBuffer = AskInput.askForCast(scanner);
                    originalTitle = AskInput.askForOriginalTitle(scanner);
                    whereToWatch = AskInput.askForWhereToWatch(scanner);
                    int seasonNumber = AskInput.askForSeasonNumber(scanner);
                    int episodeCount = AskInput.askForEpisodeCount(scanner);

                    System.out.println(journalController.register(
                            title, year, genre, yearOfEnding, castBuffer,
                            originalTitle, whereToWatch, seasonNumber, episodeCount
                    ));
                    break;

                case 4:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(View.Prompts.Colors.red + "Opção inválida " + View.Prompts.Colors.rst);
                    break;

            }
        } while (option != 4);
    }
}
