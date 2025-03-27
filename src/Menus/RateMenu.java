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
            System.out.println("2 - Escrever review");
            System.out.println("3 - Escrever review (temporada)"); //TODO: TEMP
            System.out.println(Prompts.Colors.red + "4 - Voltar" + Prompts.Colors.rst);

            option = Prompts.Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    String title = Prompts.AskInput.askForTitle(scanner);
                    double rating = Prompts.AskInput.askForRate(scanner);

                    boolean sucess = journalController.rateMedia(title, rating);

                    System.out.println((sucess) ? (Prompts.Colors.green + "Avaliação salva com sucesso!"+ Prompts.Colors.rst) :
                            (Prompts.Colors.red + "Não foi possível salvar a avaliação!" + Prompts.Colors.rst));
                    break;

                case 2:
                    String title2 = Validate.validateString(scanner);
                    String review = Validate.validateString(scanner);

                    boolean sucess2 = journalController.writeReview(title2, review);

                    System.out.println((sucess2)? (Prompts.Colors.red + "Review salvo com sucesso!"+ Prompts.Colors.rst) :
                            (Prompts.Colors.red + "Não foi possível salvar a review!" + Prompts.Colors.rst));
                    break;

                case 3:
                    break;
            }
        } while (option != 4);
    }
}
