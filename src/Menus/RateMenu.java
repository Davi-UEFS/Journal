package Menus;

import Prompts.*;
import Journal.JournalController;
import java.util.Scanner;

public class RateMenu {
    private Scanner scanner;
    private JournalController journalController;

    public RateMenu(JournalController journalController, Scanner scanner){
        this.journalController = journalController;
        this.scanner = scanner;
    }

    public void rateMiniMenu() {

        int option;

        do {
            System.out.println(Prompts.Colors.blue + "--== MENU DE AVALIAÇÃO ==--" + Prompts.Colors.rst);
            System.out.println("1 - Avaliar obra");
            System.out.println("2 - Escrever review (obra)");
            System.out.println("3 - Avaliar temporada");
            System.out.println("4 - Escrever review (temporada)");
            System.out.println(Prompts.Colors.red + "5 - Voltar" + Prompts.Colors.rst);

            option = Prompts.Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    String title = Prompts.AskInput.askForTitle(scanner);
                    double rating = Prompts.AskInput.askForRate(scanner);

                    boolean success = journalController.rateMedia(title, rating);

                    System.out.println((success) ? (Prompts.Colors.green + "Avaliação salva com sucesso!"+ Prompts.Colors.rst) :
                            (Prompts.Colors.red + "Não foi possível salvar a avaliação!" + Prompts.Colors.rst));
                    break;

                case 2:
                    String title2 = Prompts.AskInput.askForTitle(scanner);
                    String review = Prompts.AskInput.askForReview(scanner);

                    boolean success2 = journalController.writeReview(title2, review);

                    System.out.println((success2)? (Prompts.Colors.green + "Review salva com sucesso!"+ Prompts.Colors.rst) :
                            (Prompts.Colors.red + "Não foi possível salvar a review!" + Prompts.Colors.rst));
                    break;

                case 3:
                    String title3 = Prompts.AskInput.askForTitle(scanner);
                    int season_n = Prompts.AskInput.askForSeasonNumber(scanner);
                    double rating2 = Prompts.AskInput.askForRate(scanner);

                    boolean success3 = journalController.rateSeason(title3, season_n, rating2);

                    System.out.println((success3)? (Prompts.Colors.green + "Avaliação salva com sucesso!"+ Prompts.Colors.rst) :
                            (Prompts.Colors.red + "Não foi possível salvar a avaliação!" + Prompts.Colors.rst));
                    break;

                case 4:
                    String title4 = Prompts.AskInput.askForTitle(scanner);
                    int season_n2 = Prompts.AskInput.askForSeasonNumber(scanner);
                    String review2 = Prompts.AskInput.askForReview(scanner);

                    boolean success4 = journalController.reviewSeason(title4, season_n2, review2);

                    System.out.println((success4)? (Prompts.Colors.green + "Review salva com sucesso!"+ Prompts.Colors.rst) :
                            (Prompts.Colors.red + "Não foi possível salvar a review!" + Prompts.Colors.rst));
                    break;

                default:
                    System.out.println(Prompts.Colors.red + "Opção inválida " + Prompts.Colors.rst);
                    break;
            }
        } while (option != 5);
    }
}
