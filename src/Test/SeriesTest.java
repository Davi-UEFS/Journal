package Test;

import Controller.SeriesService;
import Model.Genres;
import Model.Library;
import Model.Medias.Season;
import Model.Medias.Series;
import Model.Result.Failure;
import Model.Result.IResult;
import Model.Result.Success;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SeriesTest {
    Library journal;
    SeriesService seriesService;
    @BeforeEach void setUp() {
        journal = new Library();
        seriesService = new SeriesService(journal);
    }

    @Test
    public void testRegisterSeries() {

        IResult result1 = seriesService.register(
                "Missão Explosiva", 2021, Genres.TERROR, 2024,
                new String[]{"João", "Maria", "Lucas"},
                "Ação Total",
                new String[]{"Netflix", "HBO Max"},
                1, 5);

        IResult result2 = seriesService.register(
                "Amor em Paris", 2019, Genres.AVENTURA, 2020,
                new String[]{"Pedro", "Ana"},
                "Filmes Românticos",
                new String[]{"Prime Video", "Disney+"},
                1, 7);

        IResult result3 = seriesService.register(
                "Amor em Paris", 2019, Genres.ROMANCE, 9999,
                new String[]{"Guilherme"},
                "Love in Paris",
                new String[]{"Netflix"},
                1, 10);

        assertEquals(Success.class, result1.getClass());
        assertEquals(Success.class, result2.getClass());
        assertEquals(Failure.class, result3.getClass());
        assertEquals(2, seriesService.getAllSeries().size());
    }

    @Test
    public void testAddSeason() {

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

        seriesService.register(
                "Missão Explosiva", 2021, Genres.TERROR, 2024,
                new String[]{"João", "Maria", "Lucas"},
                "Ação Total",
                new String[]{"Netflix", "HBO Max"},
                1, 5);

        Series testSeries = journal.getSeriesList().getFirst();
        assertEquals("Missão Explosiva", testSeries.getTitle());

        seriesService.markAsSeenSeason(testSeries, 1);

        // Testar avaliação de temporada
        IResult ratingResult = seriesService.rateSeason(testSeries, 1, 4.5);
        assertEquals(Success.class, ratingResult.getClass());

        // Testar review de temporada
        IResult reviewResult = seriesService.writeReviewSeason(testSeries, 1, "Excelente primeira temporada!");
        assertEquals(Success.class, reviewResult.getClass());

        // Verificar avaliação e review
        System.out.println(seriesService.showRating(testSeries));
        System.out.println(seriesService.showRatingSeason(testSeries, 1));
        System.out.println(seriesService.readReviewSeason(testSeries, 1));
    }

    @Test
    public void testSearchMethods() {

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

        seriesService.register(
                "Série Completa", 2020, Genres.ESPORTES, 2023,
                new String[]{"Ator X", "Atriz Y"},
                "Original X",
                new String[]{"Platform X"},
                1, 8);

        // Adicionar temporadas adicionais
        Series testSeries = seriesService.getAllSeries().getFirst();
        testSeries.addSeason(new Season(2, 10));
        testSeries.addSeason(new Season(3, 12));

        //Marcar apenas temporadas 1 e 2 como vistas
        seriesService.markAsSeenSeason(testSeries, 1);
        seriesService.markAsSeenSeason(testSeries, 2);

        // Avaliar diferentes temporadas
        seriesService.rateSeason(testSeries, 1, 2.0);
        seriesService.rateSeason(testSeries, 2, 4.0);
        IResult result = seriesService.rateSeason(testSeries, 3, 4.5);

        // Verificar avaliação geral (temporada nao avaliada tambem conta)
        assertEquals(2.0, journal.getSeriesList().getFirst().getRating());
        // Verificar que a temporada 3 nao foi avaliada
        assertEquals(Failure.class, result.getClass());
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