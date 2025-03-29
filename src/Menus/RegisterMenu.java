package Menus;

import Prompts.*;
import Journal.JournalController;

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

        int option;

        do {
            System.out.println(Prompts.Colors.purple + "--== MENU DE REGISTRO ==--" + Prompts.Colors.rst);
            System.out.println("1 - Registrar livro");
            System.out.println("2 - Registrar filme");
            System.out.println("3 - Registrar série");
            System.out.println(Prompts.Colors.red + "4 - Voltar" + Prompts.Colors.rst);
            option = Prompts.Validate.validateInt(scanner);

            switch (option) {
                case 1:

                    String bTitle = AskInput.askForTitle(scanner);
                    int bYear = AskInput.askForYear(scanner);
                    String bGenre = AskInput.askForGenre(scanner, journalController.availableGenres());
                    String bIsbn = AskInput.askForISBN(scanner);
                    String bAuthor = AskInput.askForAuthor(scanner);
                    String bPublisher = AskInput.askForPublisher(scanner);
                    boolean bOwned = AskInput.askForOwned(scanner);

                    journalController.registerBook(bTitle, bYear, bGenre, bIsbn, bAuthor,
                            bPublisher, bOwned);
                    System.out.println(Prompts.Colors.green + "Livro registrado com sucesso!" + Prompts.Colors.rst);

                    break;

                case 2:
                    String mTitle = AskInput.askForTitle(scanner);
                    int mYear = AskInput.askForYear(scanner);
                    String mGenre = AskInput.askForGenre(scanner, journalController.availableGenres());
                    String[] mcastBuffer = AskInput.askForCast(scanner);
                    Duration mDuration = AskInput.askForDuration(scanner);
                    String mDirector = AskInput.askForDirector(scanner);
                    String mScript = AskInput.askForScript(scanner);
                    String mOriginalTitle = AskInput.askForOriginalTitle(scanner);
                    String[] mWhereToWatch = AskInput.askForWhereToWatch(scanner);

                    journalController.registerMovie(mTitle, mYear, mGenre, mcastBuffer,
                            mDuration, mDirector, mScript, mOriginalTitle, mWhereToWatch);
                    System.out.println(Prompts.Colors.green + "Filme registrado com sucesso!" + Prompts.Colors.rst);

                    break;

                case 3:
                    String sTitle = AskInput.askForTitle(scanner);
                    int sYear = AskInput.askForYear(scanner);
                    String sGenre = AskInput.askForGenre(scanner, journalController.availableGenres());
                    int sYearOfEnding = AskInput.askForYearOfEnding(scanner);
                    String[] sCastBuffer = AskInput.askForCast(scanner);
                    String sOriginalTitle = AskInput.askForOriginalTitle(scanner);
                    String[] sWhereToWatch = AskInput.askForWhereToWatch(scanner);
                    int seasonNumber = AskInput.askForSeasonNumber(scanner);

                    journalController.registerSeries(sTitle, sYear, sGenre, sYearOfEnding,
                            sCastBuffer, sOriginalTitle, sWhereToWatch, seasonNumber);
                    System.out.println(Prompts.Colors.green + "Série registrada com sucesso!" + Prompts.Colors.rst);

                    break;

                case 4:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(Prompts.Colors.red + "Opção inválida " + Prompts.Colors.rst);
                    break;

            }
        } while (option != 4);
    }
}
