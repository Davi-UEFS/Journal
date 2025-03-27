package Menus;

import Journal.JournalController;

import java.util.Scanner;

public class MainMenu {

    private Scanner scanner = new Scanner(System.in);  //TODO: FINAL?
    private JournalController journalController;
    private DisplayMenu displayMenu;
    private RegisterMenu registerMenu;
    private RateMenu rateMenu;
    
    public MainMenu(JournalController journalController) {
        this.journalController = journalController;
        this.displayMenu = new DisplayMenu(journalController, scanner);
        this.rateMenu = new RateMenu(journalController, scanner);
        this.registerMenu = new RegisterMenu(journalController, scanner);
    }

    public void showMenu(){
        int option;

        do{
            System.out.println(Prompts.Colors.cyan + "--== DIÁRIO CULTURAL ==--" + Prompts.Colors.rst);
            System.out.println("1 - Registrar ");
            System.out.println("2 - Avaliar ");
            System.out.println("3 - Ver");
            System.out.println(Prompts.Colors.red + "4 - Encerrar " + Prompts.Colors.rst);

            option = Prompts.Validate.validateInt(scanner);

            switch (option) {
                case 1:
                    registerMenu.registerMiniMenu(journalController);

                    break;

                case 2:
                    rateMenu.rateMiniMenu();
                    break;

                case 3:
                    displayMenu.displayMiniMenu();
                    break;

                case 4:
                    //Instructions here
                    break;
                
                default:
                    break;
            }
            
        }while(option != 4);
        scanner.close();
    }


}
