package View;

import Controller.JournalController;
import java.util.Scanner;

public class RateMenu {
    private final Scanner scanner;
    private final JournalController journalController;

    public RateMenu(JournalController journalController, Scanner scanner){
        this.journalController = journalController;
        this.scanner = scanner;
    }

    public void rateMiniMenu() {

        int option;
        String title, review;
        int seasonNumber;
        double rating;

        do {
            System.out.println(View.Prompts.Colors.blue + "--== MENU DE AVALIAÇÃO ==--" + View.Prompts.Colors.rst);
            System.out.println("1 - Avaliar obra");
            System.out.println("2 - Escrever review (obra)");
            System.out.println("3 - Avaliar temporada");
            System.out.println("4 - Escrever review (temporada)");
            System.out.println(View.Prompts.Colors.red + "5 - Voltar" + View.Prompts.Colors.rst);

            option = View.Prompts.Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    rating = View.Prompts.AskInput.askForRate(scanner);

                    System.out.println(journalController.rate(title, rating));

                    break;

                case 2:
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    review = View.Prompts.AskInput.askForReview(scanner);
                    System.out.println(journalController.writeReview(title, review));
                    break;

                case 3:
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    seasonNumber = View.Prompts.AskInput.askForSeasonNumber(scanner);
                    rating = View.Prompts.AskInput.askForRate(scanner);

                    System.out.println(journalController.rate(title, seasonNumber, rating));

                    break;

                case 4:
                    title = View.Prompts.AskInput.askForTitle(scanner);
                    seasonNumber = View.Prompts.AskInput.askForSeasonNumber(scanner);
                    review = View.Prompts.AskInput.askForReview(scanner);

                    System.out.println(journalController.writeReview(title, seasonNumber, review));
                    break;

                default:
                    System.out.println(View.Prompts.Colors.red + "Opção inválida " + View.Prompts.Colors.rst);
                    break;
            }
        } while (option != 5);
    }
}
