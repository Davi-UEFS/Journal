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
            System.out.println(Inputs.Colors.blue + "--== MENU DE AVALIAÇÃO ==--" + Inputs.Colors.rst);
            System.out.println("1 - Avaliar obra");
            System.out.println("2 - Escrever review");
            System.out.println("3 - Escrever review (temporada)"); //TODO: TEMP
            System.out.println(Inputs.Colors.red + "4 - Voltar" + Inputs.Colors.rst);

            option = Inputs.Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    String title = Inputs.AskInput.askForTitle(scanner);
                    double rating = Inputs.AskInput.askForRate(scanner);

                    boolean sucess = journalController.rateMedia(title, rating);

                    System.out.println((sucess) ? (Inputs.Colors.green + "Avaliação salva com sucesso!"+ Inputs.Colors.rst) :
                            (Inputs.Colors.red + "Não foi possível salvar a avaliação!" + Inputs.Colors.rst));
                    break;

                case 2:
                    String title2 = Validate.validateString(scanner);
                    String review = Validate.validateString(scanner);

                    boolean sucess2 = journalController.writeReview(title2, review);

                    System.out.println((sucess2)? (Inputs.Colors.red + "Review salvo com sucesso!"+ Inputs.Colors.rst) :
                            (Inputs.Colors.red + "Não foi possível salvar a review!" + Inputs.Colors.rst));
                    break;

                case 3:
                    break;
            }
        } while (option != 4);
    }
}
