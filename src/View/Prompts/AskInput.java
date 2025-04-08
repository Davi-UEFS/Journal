package View.Prompts;

import java.time.Duration;
import java.util.Scanner;
import Controller.JournalController;

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
        int year = Validate.validateInt(scanner);
        while(year > 2025){
            System.out.print("Ano inválido! Digite novamente: ");
            year = Validate.validateInt(scanner);
        }
        return year; // Convertendo para int
    }

    public static int askForYearOfEnding(Scanner scanner) {
        System.out.print("Digite o ano de encerramento (9999 se ainda está em lançamento): ");
        int year = Validate.validateInt(scanner);
        while((year > 2025 && year < 9999) || (year > 9999)){
            System.out.print("Ano inválido! Digite novamente: ");
            year = Validate.validateInt(scanner);
        }
        return year; // Convertendo para int
    }


    public static boolean askForOwned(Scanner scanner){
        System.out.print("Você já leu este livro? (S/N): ");
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
        return Validate.validateInt(scanner);
    }
    public static int askForGenre(Scanner scanner) {
        JournalController.showGenres();
        System.out.print("Digite um dos gêneros acima: ");
        int wantedGenre = Validate.validateInt(scanner);
        while(wantedGenre < 1 || wantedGenre > 12){
            wantedGenre = Validate.validateInt(scanner);
            System.out.print("Opção inválida! Digite novamente: ");
        }

        return wantedGenre;
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

    public static String askForReview(Scanner scanner){
        System.out.println("Digite sua review: ");
        return Validate.validateString(scanner);
    }
}
