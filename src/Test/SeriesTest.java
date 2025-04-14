package Test;

import Controller.SeriesService;
import Model.Genres;
import Model.Library;
import Model.Medias.Season;
import Model.Medias.Series;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SeriesTest {

    @Test
    public void testRegisterSeries() {
        Library journal = new Library();
        SeriesService seriesService = new SeriesService(journal);

        String result1 = seriesService.register(
                "Missão Explosiva", 2021, Genres.TERROR, 2024,
                new String[]{"João", "Maria", "Lucas"},
                "Ação Total",
                new String[]{"Netflix", "HBO Max"},
                1, 5);

        String result2 = seriesService.register(
                "Amor em Paris", 2019, Genres.AVENTURA, 2020,
                new String[]{"Pedro", "Ana"},
                "Filmes Românticos",
                new String[]{"Prime Video", "Disney+"},
                1, 7);

        assertEquals("Série registrada com sucesso!", result1);
        assertEquals("Série registrada com sucesso!", result2);
        assertEquals(2, seriesService.getAllSeries().size());
    }

    @Test
    public void testAddSeason() {
        Library journal = new Library();
        SeriesService seriesService = new SeriesService(journal);

        seriesService.register(
                "Missão Explosiva", 2021, Genres.TERROR, 2024,
                new String[]{"João", "Maria", "Lucas"},
                "Ação Total",
                new String[]{"Netflix", "HBO Max"},
                1, 5);

        Series series = seriesService.getAllSeries().getFirst();
        int initialSeasons = series.getNumberOfSeasons();

        series.addSeason(new Season(2, 8));

        assertEquals(initialSeasons + 1, series.getNumberOfSeasons());
    }

    @Test
    public void testRateAndReview() {
        Library journal = new Library();
        SeriesService seriesService = new SeriesService(journal);

        seriesService.register(
                "Missão Explosiva", 2021, Genres.TERROR, 2024,
                new String[]{"João", "Maria", "Lucas"},
                "Ação Total",
                new String[]{"Netflix", "HBO Max"},
                1, 5);

        // Testar avaliação de temporada
        String ratingResult = seriesService.rateSeason("Missão Explosiva", 1, 4.5);
        assertEquals("Avaliação salva com sucesso", ratingResult);

        // Testar review de temporada
        String reviewResult = seriesService.writeReviewSeason("Missão Explosiva", 1, "Excelente primeira temporada!");
        assertEquals("Review salva com sucesso", reviewResult);

        // Verificar avaliação e review
        System.out.println(seriesService.showRating("Missão Explosiva"));
        System.out.println(seriesService.showRatingSeason("Missão Explosiva", 1));
        System.out.println(seriesService.readReviewSeason("Missão Explosiva", 1));
    }

    @Test
    public void testSearchMethods() {
        Library journal = new Library();
        SeriesService seriesService = new SeriesService(journal);

        // Adicionar várias séries para testar buscas
        seriesService.register("Série A", 2020, Genres.AÇÃO, 2022,
                new String[]{"Ator 1"}, "Original A",
                new String[]{"Platform A"}, 1, 10);
        seriesService.register("Série B", 2021, Genres.COMÉDIA, 2023,
                new String[]{"Ator 2"}, "Original B",
                new String[]{"Platform B"}, 1, 8);
        seriesService.register("Série C", 2020, Genres.AÇÃO, 2021,
                new String[]{"Ator 3"}, "Original C",
                new String[]{"Platform C"}, 1, 12);

        // Testar busca por título
        List<Series> searchResults = seriesService.searchByTitle("Série", seriesService.getAllSeries());
        printSeriesList(searchResults);
        assertEquals(3, searchResults.size());

        // Testar busca por ano
        List<Series> yearResults = seriesService.searchByYear(2020, seriesService.getAllSeries());
        assertEquals(2, yearResults.size());

        // Testar busca por gênero
        List<Series> genreResults = seriesService.searchByGenre(Genres.AÇÃO, seriesService.getAllSeries());
        assertEquals(2, genreResults.size());
    }

    @Test
    public void testSeasonOperations() {
        Library journal = new Library();
        SeriesService seriesService = new SeriesService(journal);

        seriesService.register(
                "Série Completa", 2020, Genres.ESPORTES, 2023,
                new String[]{"Ator X", "Atriz Y"},
                "Original X",
                new String[]{"Platform X"},
                1, 8);

        // Adicionar temporadas adicionais
        Series series = seriesService.getAllSeries().getFirst();
        series.addSeason(new Season(2, 10));
        series.addSeason(new Season(3, 12));

        // Avaliar diferentes temporadas
        seriesService.rateSeason("Série Completa", 1, 3.5);
        seriesService.rateSeason("Série Completa", 2, 4.0);
        seriesService.rateSeason("Série Completa", 3, 4.5);

        // Verificar avaliação geral
        assertEquals(4.0, journal.getSeriesList().getFirst().getRating());
        assertEquals(3, series.getNumberOfSeasons());
    }

    private void printSeriesList(List<Series> seriesList) {
        for (Series series : seriesList) {
            System.out.println(series.toString());
            for (Season season : series.getSeasons()) {
                System.out.print("\t");
                System.out.println(season.toString());
            }
        }
    }
}