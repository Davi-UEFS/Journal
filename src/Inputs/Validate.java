package Inputs;

import java.util.Scanner;

public class Validate {

    public static int validateInt(Scanner scanner){
        boolean validInt;
        int num = 0;
        do {
            validInt = true;
            try {
                num = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                validInt = false;
                System.out.println("Erro. Digite um numero");
            }
        } while (!validInt);

        return num;
    }

    public static double validateDouble(Scanner scanner){
        boolean validDouble;
        double dbl = 0;
        do {
            validDouble = true;
            try {
                dbl = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                validDouble = false;
                System.out.println("Erro. Digite um numero"); //TODO: GRAM
            }
        } while (!validDouble);

        return dbl;
    }

    public static String validateString(Scanner scanner){

        String str;

        str = scanner.nextLine();
        while(str.isBlank()){
            System.out.println("Erro. Digite um texto valido"); //TODO: GRAM
            str = scanner.nextLine();
        }
        return str.trim();
    }

    public static boolean validateBoolean(Scanner scanner){

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("s") || input.equals("sim") || input.equals("true")){
                return true;

            } else if (input.equals("n") || input.equals("nao") || input.equals("false")){
                return false;
            }

            System.out.println("Erro: Digite apenas 'true' ou 'false'.");
        }

    }

}
