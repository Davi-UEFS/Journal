package View;

import Controller.*;
import Model.Medias.*;
import Model.Genres;
import View.Prompts.*;

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
        Book selectedBook;
        Movie selectedMovie;
        Series selectedSeries;

        do{
            System.out.println(Colors.green + "--== MENU DE DISPLAY ==--" + Colors.rst);
            System.out.println("1 - Ver avaliações/reviews (livro)");
            System.out.println("2 - Ver avaliações/reviews (filme)");
            System.out.println("3 - Ver avaliações/reviews (série)");
            System.out.println("4 - Ver livros cadastrados");
            System.out.println("5 - Ver filmes cadastrados");
            System.out.println("6 - Ver series cadastradas");
            System.out.println(Colors.red + "0 - Voltar" + Colors.rst);

            option = Validate.validateInt(scanner);

            switch (option) {

                case 1:
                    if(bookService.getAllBooks().isEmpty())
                        System.out.println("Você não possui livros cadastrados.");
                    else {
                        selectedBook = AskInput.selectFromList(scanner, bookService.getAllBooks());

                        System.out.println(bookService.readReview(selectedBook));
                        System.out.println(bookService.showRating(selectedBook));
                    }
                    break;

                case 2:
                    if(movieService.getAllMovies().isEmpty())
                        System.out.println("Você não possui filmes cadastrados");
                    else {
                        selectedMovie = AskInput.selectFromList(scanner, movieService.getAllMovies());

                        System.out.println(movieService.readReview(selectedMovie));
                        System.out.println(movieService.showRating(selectedMovie));
                    }
                    break;

                case 3:
                    if(seriesService.getAllSeries().isEmpty())
                        System.out.println("Você não possui séries cadastradas");

                    else {
                        selectedSeries = AskInput.selectFromList(scanner, seriesService.getAllSeries());
                        seasonNumber = AskInput.askForSeasonNumber(scanner);
                        System.out.println(seriesService.readReviewSeason(selectedSeries, seasonNumber));
                        //Nota da temporada
                        System.out.println(seriesService.showRatingSeason(selectedSeries, seasonNumber));
                        //Media das notas das temporadas
                        System.out.println(seriesService.showRating(selectedSeries));
                    }
                    break;

                case 4:
                    if(bookService.getAllBooks().isEmpty())
                        System.out.println("Você não possui livros cadastrados.");
                    else
                        listByMiniMenu(bookService, bookService.getAllBooks());
                    break;

                case 5:
                    if(movieService.getAllMovies().isEmpty())
                        System.out.println("Você não possui filmes cadastrados");
                    else
                        listByMiniMenu(movieService, movieService.getAllMovies());
                    break;

                case 6:
                    if(seriesService.getAllSeries().isEmpty())
                        System.out.println("Você não possui séries cadastradas");

                    else
                        listByMiniMenu(seriesService, seriesService.getAllSeries());
                    break;

                case 0:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(Colors.red + "Opção inválida" + Colors.rst);
                    break;

            }
        }while (option!=0);
    }

    private <T extends Media> void listByMiniMenu(CommonService<T> service, List<T> mediaList){
        int option;
        Map<Genres, List<T>> mapGenreMedia;
        Map<Integer, List<T>> mapIntMedia;

        do{
            showListOptions();
            option = Validate.validateInt(scanner);

            switch (option) {

                case 1:
                    List<T> listA = service.sortAscending(mediaList);
                    for(Media i:listA)
                        System.out.println(i.toString());
                    break;

                case 2:
                    List<T> listD = service.sortAscending(mediaList);
                    for(Media i:listD)
                        System.out.println(i.toString());
                    break;

                case 3:
                    mapGenreMedia = service.mapByGenreRate(mediaList, true);
                    printMapGenreMedia(mapGenreMedia);
                    break;

                case 4:
                    mapGenreMedia = service.mapByGenreRate(mediaList, false);
                    printMapGenreMedia(mapGenreMedia);
                    break;

                case 5:
                    mapIntMedia = service.mapByYearRate(mediaList, true, true);
                    printMapYearMedia(mapIntMedia);
                    break;

                case 6:
                    mapIntMedia = service.mapByYearRate(mediaList, true, false);
                    printMapYearMedia(mapIntMedia);
                    break;

                case 7:
                    mapIntMedia = service.mapByYearRate(mediaList, false, true);
                    printMapYearMedia(mapIntMedia);
                    break;

                case 8:
                    mapIntMedia = service.mapByYearRate(mediaList, false, false);
                    printMapYearMedia(mapIntMedia);
                    break;

                case 0:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println(Colors.red + "Opção inválida" + Colors.rst);
                    break;
            }
        }while (option!=0);
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
        System.out.println(Colors.green + "--== MENU DE DISPLAY ==--" + Colors.rst);
        System.out.println("1 - Ver todos (crescente) ");
        System.out.println("2 - Ver todos (decrescente)");
        System.out.println("3 - Por gênero (crescente)");
        System.out.println("4 - Por gênero (decrescente)");
        System.out.println("5 - Por mais recente (crescente)");
        System.out.println("6 - Por mais recente (decrescente)");
        System.out.println("7 - Por menos recente (crescente)");
        System.out.println("8 - Por menos recente (decrescente)");
        System.out.println(Colors.red + "0 - Voltar" + Colors.rst);

    }

}
