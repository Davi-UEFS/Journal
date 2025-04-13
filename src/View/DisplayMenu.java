package View;

import Controller.BookService;
import Controller.MediaService;
import Controller.MovieService;
import Controller.SeriesService;
import Model.Media.*;
import Model.Genres;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DisplayMenu {
    private final Scanner scanner;
    private final BookService bookService;
    private final MovieService movieService;
    private final SeriesService seriesService;

    public DisplayMenu(BookService bookService, MovieService movieService,
                        SeriesService seriesService, Scanner scanner) {
        this.bookService = bookService;
        this.movieService = movieService;
        this.seriesService = seriesService;
        this.scanner = scanner;
    }

    public void show() {

        int option;
        String title;
        int seasonNumber;

        do{
            System.out.println(View.Prompts.Colors.green + "--== MENU DE DISPLAY ==--" + View.Prompts.Colors.rst);
            System.out.println("1 - Ver avaliações/reviews (livro)");
            System.out.println("2 - Ver avaliações/reviews (filme)");
            System.out.println("3 - Ver avaliações/reviews (série)");
            System.out.println("4 - Ver livros cadastrados");
            System.out.println("5 - Ver filmes cadastrados");
            System.out.println("6 - Ver series cadastradas");
            System.out.println(View.Prompts.Colors.red + "7 - Voltar" + View.Prompts.Colors.rst);

            option = View.Prompts.Validate.validateInt(scanner);

            switch (option) {

                case 1:

                    title = View.Prompts.AskInput.askForTitle(scanner);

                    System.out.println(bookService.readReview(title));
                    System.out.println(bookService.showRating(title));
                    break;

                case 2:

                    title = View.Prompts.AskInput.askForTitle(scanner);

                    System.out.println(movieService.readReview(title));
                    System.out.println(movieService.showRating(title));
                    break;

                case 3:

                    title = View.Prompts.AskInput.askForTitle(scanner);
                    seasonNumber = View.Prompts.AskInput.askForSeasonNumber(scanner);
                    System.out.println(seriesService.readReview(title, seasonNumber));
                    //Nota da temporada
                    System.out.println(seriesService.showRating(title, seasonNumber));
                    //Media das notas das temporadas
                    System.out.println(seriesService.showRating(title));
                    break;

                case 4:
                    listByMiniMenu(bookService, bookService.allBooks());
                    break;

                case 5:
                    listByMiniMenu(movieService, movieService.allMovies());
                    break;

                case 6:
                    listByMiniMenu(seriesService, seriesService.allSeries());
                    break;

                case 7:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(View.Prompts.Colors.red + "Opção inválida" + View.Prompts.Colors.rst);
                    break;

            }
        }while (option!=7);
    }

    private <T extends Media> void listByMiniMenu(MediaService<T> service, List<T> mediaList){
        int option;
        Map<Genres, List<T>> mapGenreMedia;
        Map<Integer, List<T>> mapIntMedia;

        do{
            showListOptions();
            option = View.Prompts.Validate.validateInt(scanner);

            switch (option) {

                case 1:

                    System.out.println(service.sortAscending(mediaList));
                    break;

                case 2:
                    System.out.println(service.sortDescending(mediaList));
                    break;

                case 3:

                    mapGenreMedia = service.byGenreAscendingRate(mediaList);
                    printMapGenreMedia(mapGenreMedia);
                    break;

                case 4:
                    mapGenreMedia = service.byGenreDescendingRate(mediaList);
                    printMapGenreMedia(mapGenreMedia);
                    break;

                case 5:
                    mapIntMedia = service.mapByAscendingYearAscendingRate(mediaList);
                    printMapYearMedia(mapIntMedia);
                    break;

                case 6:
                    mapIntMedia = service.mapByAscendingYearDescendingRate(mediaList);
                    printMapYearMedia(mapIntMedia);
                    break;

                case 7:
                    mapIntMedia = service.mapByDescendingYearAscendingRate(mediaList);
                    printMapYearMedia(mapIntMedia);
                    break;

                case 8:
                    mapIntMedia = service.mapByDescendingYearDescendingRate(mediaList);
                    printMapYearMedia(mapIntMedia);
                    break;

                case 9:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(View.Prompts.Colors.red + "Opção inválida" + View.Prompts.Colors.rst);
                    break;
            }
        }while (option!=9);
    }

    private <T extends Media> void printMapGenreMedia(Map<Genres, List<T>> mapGenreMedia) {

        for (Map.Entry<Genres, List<T>> thisGenreMedia : mapGenreMedia.entrySet()) {
            System.out.println(thisGenreMedia.getKey());
            for (Media media : thisGenreMedia.getValue())
                System.out.println(media + "\n");
        }
    }

    private <T extends Media> void printMapYearMedia(Map<Integer, List<T>> mapYearMedia) {
        for (Map.Entry<Integer, List<T>> thisYearMedia : mapYearMedia.entrySet()) {

            System.out.println(thisYearMedia.getKey());
            for (Media media : thisYearMedia.getValue())
                System.out.println(media + "\n");
        }
    }

    private void showListOptions(){
        System.out.println(View.Prompts.Colors.green + "--== MENU DE DISPLAY ==--" + View.Prompts.Colors.rst);
        System.out.println("1 - Ver todos (crescente) ");
        System.out.println("2 - Ver todos (decrescente)");
        System.out.println("3 - Por gênero (crescente)");
        System.out.println("4 - Por gênero (decrescente)");
        System.out.println("5 - Por mais recente (crescente)");
        System.out.println("6 - Por mais recente (decrescente)");
        System.out.println("5 - Por menos recente (crescente)");
        System.out.println("6 - Por menos recente (decrescente)");
        System.out.println(View.Prompts.Colors.red + "9 - Voltar" + View.Prompts.Colors.rst);

    }

}
