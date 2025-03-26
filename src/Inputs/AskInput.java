package Inputs;

import java.time.Duration;
import java.util.Scanner;

public class AskInput {

    public static String askForTitle(Scanner scanner) {
        System.out.print("Digite o título da obra: ");
        return Validate.validateString(scanner);
    }
    
    public static String askForAuthor(Scanner scanner) {
        System.out.print("Digite o nome do autor: ");
        return Validate.validateString(scanner);
    }
    public static String askForISBN(Scanner scanner) {
        System.out.print("Digite o ISBN da obra (XXX-9999999-9): ");
        return Validate.validateString(scanner);
    }
    
    public static String askForPublisher(Scanner scanner) {
        System.out.print("Digite o nome do editora: ");
        return Validate.validateString(scanner);
    }
    
    public static Duration askForDuration(Scanner scanner){
        System.out.print("Digite a duração do filme (em minutos): ");
        return Duration.ofMinutes(Validate.validateInt(scanner)); // Convertendo para int
    }
    
    public static String askForDirector(Scanner scanner){
        System.out.print("Digite o nome do diretor: ");
        return Validate.validateString(scanner);
    }
    public static int askForYear(Scanner scanner) {
        System.out.print("Digite o ano de lançamento (YYYY): ");
        return Validate.validateInt(scanner); // Convertendo para int
    }

    public static int askForYearOfEnding(Scanner scanner) {
        System.out.print("Digite o ano de encerramento (9999 se ainda está em lançamento): ");
        return Validate.validateInt(scanner); // Convertendo para int
    }

    public static String askForGenre(Scanner scanner) {
        System.out.print("Digite o gênero da obra: ");
        return Validate.validateString(scanner);
    }
    
    public static boolean askForOwned(Scanner scanner){
        System.out.print("Você já leu este livro? (true/false) ");
        return Validate.validateBoolean(scanner);
    }
    
    public static String[] askForCast(Scanner scanner) {
        System.out.println("Digite o elenco da obra: (Fulano, Ciclano, ...)");
        return Validate.validateString(scanner).split(", ");
    }

    public static String askForOriginalTitle(Scanner scanner) {
        System.out.println("Digite o título original da obra: ");
        return Validate.validateString(scanner);
    }

    public static String askForScript(Scanner scanner){
        System.out.println("Digite a sinopse do filme: ");
        return Validate.validateString(scanner);
    }
    
    public static String[] askForWhereToWatch(Scanner scanner) {
        System.out.println("Digite as plataformas onde a obra está disponível: (Streaming1, Streaming2, ...)");
        return Validate.validateString(scanner).split(", ");
    }
    
    public static int askForSeasonNumber(Scanner scanner){
        System.out.print("Digite o número da temporada: ");
        return Validate.validateInt(scanner); // Convertendo para int
    }

    public static double askForRate(Scanner scanner){

        System.out.println("Digite a nota (0 a 5) ");

        double rate = Validate.validateDouble(scanner);

        while(rate < 0 || rate > 5) {
            rate = Validate.validateDouble(scanner);
            System.out.println("Digite uma nota entre 0 e 5");
        }
        return rate;
    }
}
