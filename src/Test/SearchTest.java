package Test;

import Journal.*;
import Media.Book;
import Media.Season;
import Media.Series;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SearchTest {

    @Test
    public void TestSearch() {
        JournalModel journal = new JournalModel();
        JournalController journalController = new JournalController(journal);
        journalController.registerBook("2050 Classes", 2025, "Educação",
                "12345", "Davi", "PBL Books", true);

        journalController.registerBook("Cavalos 2050", 2023, "Educação",
                "12345", "Davi", "PBL Books", true);
        List<Book> bookList = journalController.searchBook("2050");
        printBookList(bookList);

        System.out.println("fodase");

        String[] cast = {"Carlinhos", "Dalva", "Davi"};
        String[] where = {"Metflix", "Matagal"};

        journalController.registerSeries("As Aventuras de Carlinhos", 2020, "Pornô", 2050,
                cast, "Ocolast 2", where, 1);
        journalController.registerSeries("As Aventuras de Nycolas", 2013, "Ação", 2020,
                cast, "2 Girls 1 Cup", where, 1);


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