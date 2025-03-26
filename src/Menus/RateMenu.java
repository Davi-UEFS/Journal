package Menus;

import Inputs.*;
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

            System.out.println("1 - Avaliar obra");
            System.out.println("2 - Escrever review");
            System.out.println("3 - Escrever review (temporada)"); //TODO: TEMP
            System.out.println("4 - Voltar");

            option = Inputs.Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    String title = Inputs.AskInput.askForTitle(scanner);
                    double rating = Inputs.AskInput.askForRate(scanner);

                    boolean sucess = journalController.rateMedia(title, rating);

                    System.out.println((sucess) ? "Avaliacao salva com sucesso" :
                            "Nao foi possivel salvar a avaliacao"); //TODO: GRAM
                    break;

                case 2:
                    String title2 = Validate.validateString(scanner);
                    String review = Validate.validateString(scanner);

                    boolean sucess2 = journalController.writeReview(title2, review);

                    System.out.println((sucess2)? "Review salvo com sucesso" :
                            "Nao foi possivel salvar a review"); //TODO: GRAM
                    break;

                case 3:
                    break;
            }
        } while (option != 4);
    }
}
