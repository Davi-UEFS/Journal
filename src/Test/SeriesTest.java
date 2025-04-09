package Test;

import Controller.JournalController;
import Model.Library;
import Model.Media.Season;
import Model.Media.Series;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SeriesTest {

    @Test
    public void testSearchSeries() {
        Library journal = new Library();
        JournalController journalController = new JournalController(journal);

        journalController.register("Missão Explosiva", 2021, 1, 2024,
                new String[]{"João", "Maria", "Lucas"},
                "Ação Total",
                new String[]{"Netflix", "HBO Max"},
                2);

        journalController.register("Amor em Paris", 2019, 3, 2020,
                new String[]{"Pedro", "Ana"},
                "Filmes Românticos",
                new String[]{"Prime Video", "Disney+"},
                1);

        journalController.register("Invasão Alien", 2023, 7, 2025,
                new String[]{"Carlos", "Julia", "Miguel", "Laura"},
                "Sci-Fi Productions",
                new String[]{"HBO Max", "Prime Video", "Netflix"},
                4);


        Season seasonMissao = new Season(1);
        journalController.allSeries().getFirst().addSeason(seasonMissao);

        List<Series> seriesList = journalController.searchSeries("is");
        assertEquals(2, seriesList.size()); //Dois filmes com "is"
        System.out.println("Series com 'is' :");
        printSeriesList(seriesList);

        assertEquals(2, journalController.allSeries().getFirst().getNumberOfSeasons());

    }

    @Test
    public void checkHash(){
        Library journal = new Library();
        JournalController journalController = new JournalController(journal);

        journalController.register("Missão Explosiva", 2021, 1, 2024,
                new String[]{"João", "Maria", "Lucas"},
                "Ação Total",
                new String[]{"Netflix", "HBO Max"},
                2);

        System.out.println(journal.getSeriesList().getFirst().getHashCode());
    }

    private void printSeriesList(List<Series> seriesList) {
        for(Series series: seriesList){
            System.out.println(series.toString());
            for(Season season: series.getSeasons()) {
                System.out.print("\t");
                System.out.println(season.toString());
            }

        }
    }
}
