package Test;

import Controller.*;
import Model.Library;
import Model.Media.Book;
import Model.Media.Season;
import Model.Media.Series;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SearchTest {
    //TODO IMPORTANTE: MUDAR O NOME DESSES NEGOCIO AE
    @Test
    public void TestSearch() {
        Library journal = new Library();
        JournalController journalController = new JournalController(journal);
        journalController.register("2050 Classes", 2025, 5,
                "12345", "Davi", "PBL Books", true);

        journalController.register("Cavalos 2050", 2023, 2,
                "12345", "Davi", "PBL Books", true);
        List<Book> bookList = journalController.searchBook("2050");
        printBookList(bookList);

        System.out.println("fodase");

        String[] cast = {"Carlinhos", "Dalva", "Davi"};
        String[] where = {"Netflix", "Matagal"};

        journalController.register("As Aventuras de Carlinhos", 2020, 6, 2050,
                cast, "Ocolast 2", where, 5);
        journalController.register("As Aventuras de Nycolas", 2013, 3, 2020,
                cast, "Tomar no teus inferno", where, 1);

        Season season = new Season(2);
        journalController.allSeries().getFirst().addSeason(season);
        List<Series> seriesList = journalController.searchSeries("Aven");
        printSeriesList(seriesList);
    }

    private void printBookList(List<Book> bookList) {
        for(Book book: bookList)
            System.out.printf("Titulo: %s (%d)\n", book.getTitle(), book.getYear());

    }

    private void printSeriesList(List<Series> seriesList) {
        for(Series series: seriesList){
            System.out.printf("Titulo: %s (%d-%d)\n", series.getTitle(), series.getYear(), series.getYearOfEnding());
            for(Season season: series.getSeasons()) {
                System.out.print("\t");
                System.out.printf("Temporada: %d \n", season.getSeasonNumber());
            }

        }
    }
}