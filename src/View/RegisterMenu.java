package View;

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
                case 1:

                    String bTitle = AskInput.askForTitle(scanner);
                    int bYear = AskInput.askForYear(scanner);
                    int bGenre = AskInput.askForGenre(scanner);
                    String bIsbn = AskInput.askForISBN(scanner);
                    String bAuthor = AskInput.askForAuthor(scanner);
                    String bPublisher = AskInput.askForPublisher(scanner);
                    boolean bOwned = AskInput.askForOwned(scanner);

                    System.out.println(journalController.register(bTitle, bYear, bGenre, bIsbn, bAuthor,
                            bPublisher, bOwned));


                    break;

                case 2:
                    String mTitle = AskInput.askForTitle(scanner);
                    int mYear = AskInput.askForYear(scanner);
                    int mGenre = AskInput.askForGenre(scanner);
                    String[] mcastBuffer = AskInput.askForCast(scanner);
                    Duration mDuration = AskInput.askForDuration(scanner);
                    String mDirector = AskInput.askForDirector(scanner);
                    String mScript = AskInput.askForScript(scanner);
                    String mOriginalTitle = AskInput.askForOriginalTitle(scanner);
                    String[] mWhereToWatch = AskInput.askForWhereToWatch(scanner);

                    System.out.println(journalController.register(mTitle, mYear, mGenre, mcastBuffer,
                            mDuration, mDirector, mScript, mOriginalTitle, mWhereToWatch));

                    break;

                case 3:
                    String sTitle = AskInput.askForTitle(scanner);
                    int sYear = AskInput.askForYear(scanner);
                    int sGenre = AskInput.askForGenre(scanner);
                    int sYearOfEnding = AskInput.askForYearOfEnding(scanner);
                    String[] sCastBuffer = AskInput.askForCast(scanner);
                    String sOriginalTitle = AskInput.askForOriginalTitle(scanner);
                    String[] sWhereToWatch = AskInput.askForWhereToWatch(scanner);
                    int seasonNumber = AskInput.askForSeasonNumber(scanner);
                    int episodeCount = AskInput.askForEpisodeCount(scanner);

                    System.out.println(journalController.register(sTitle, sYear, sGenre, sYearOfEnding,
                            sCastBuffer, sOriginalTitle, sWhereToWatch, seasonNumber, episodeCount));

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
