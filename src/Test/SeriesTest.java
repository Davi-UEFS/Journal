package Test;

import Controller.SeriesService;
import Model.Enums.Genres;
import Model.Library;
import Model.Medias.Movie;
import Model.Medias.Season;
import Model.Medias.Series;
import Model.Result.Failure;
import Model.Result.IResult;
import Model.Result.Success;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
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
                1, 5, 2021);

        IResult result2 = seriesService.register(
                "Amor em Paris", 2019, Genres.AVENTURA, 2020,
                new String[]{"Pedro", "Ana"},
                "Filmes Românticos",
                new String[]{"Prime Video", "Disney+"},
                1, 7, 2019);

        IResult result3 = seriesService.register(
                "Amor em Paris", 2019, Genres.ROMANCE, 9999,
                new String[]{"Guilherme"},
                "Love in Paris",
                new String[]{"Netflix"},
                1, 10, 2019);

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
                1, 5, 2021);

        Series series = seriesService.getAllSeries().getFirst();
        int initialSeasons = series.getNumberOfSeasons();

        series.addSeason(new Season(2, 8, 2023));

        assertEquals(initialSeasons + 1, series.getNumberOfSeasons());
    }

    @Test
    public void testRateAndReview() {

        seriesService.register(
                "Missão Explosiva", 2021, Genres.TERROR, 2024,
                new String[]{"João", "Maria", "Lucas"},
                "Ação Total",
                new String[]{"Netflix", "HBO Max"},
                1, 5, 2021);

        Series testSeries = journal.getSeriesList().getFirst();
        assertEquals("Missão Explosiva", testSeries.getTitle());

        seriesService.markAsSeenSeason(testSeries, 1);

        // Testar avaliação de temporada
        IResult ratingResult = seriesService.rateSeason(testSeries, 1, 4);
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
                new String[]{"Platform A"}, 1, 10, 2020);
        seriesService.register("Série B", 2021, Genres.COMÉDIA, 2023,
                new String[]{"Ator 2"}, "Original B",
                new String[]{"Platform B"}, 1, 8, 2021);
        seriesService.register("Série C", 2020, Genres.AÇÃO, 2021,
                new String[]{"Ator 3"}, "Original C",
                new String[]{"Platform C"}, 1, 12, 2020);

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
                1, 8, 2020);

        // Adicionar temporadas adicionais
        Series testSeries = seriesService.getAllSeries().getFirst();
        testSeries.addSeason(new Season(2, 10, 2021));
        testSeries.addSeason(new Season(3, 12, 2022));

        //Marcar apenas temporadas 1 e 2 como vistas
        seriesService.markAsSeenSeason(testSeries, 1);
        seriesService.markAsSeenSeason(testSeries, 2);

        // Avaliar diferentes temporadas
        seriesService.rateSeason(testSeries, 1, 2);
        seriesService.rateSeason(testSeries, 2, 4);
        IResult result = seriesService.rateSeason(testSeries, 3, 4);

        // Verificar avaliação geral (temporada nao avaliada tambem conta)
        assertEquals(2, journal.getSeriesList().getFirst().getRating());
        // Verificar que a temporada 3 nao foi avaliada
        assertEquals(Failure.class, result.getClass());
    }

    @Test
    public void testSearchByActor() {
        seriesService.register("Série A", 2020, Genres.AÇÃO, 2022,
                new String[]{"Ator 1, Ator 2"}, "Original A",
                new String[]{"Platform A"}, 1, 10, 2020);
        seriesService.register("Série B", 2021, Genres.COMÉDIA, 2023,
                new String[]{"Ator 2, Ator 3"}, "Original B",
                new String[]{"Platform B"}, 1, 8, 2021);

        Series seriesA = seriesService.getAllSeries().getFirst(); // Série A
        Series seriesB = seriesService.getAllSeries().getLast(); // Série B

        List<Series> seriesList1 = seriesService.searchByActor("Ator 1"); // Série A
        List<Series> seriesList2 = seriesService.searchByActor("Ator 2"); // Séries A e B
        List<Series> seriesList3 = seriesService.searchByActor("Ator 3"); // Série B
        List<Series> seriesList4 = seriesService.searchByActor("Ator 4"); // N/A

        assertEquals(1, seriesList1.size());
        assertEquals(2, seriesList2.size());
        assertEquals(1, seriesList3.size());
        assertEquals(0, seriesList4.size());

        List<Series> seriesListA = Collections.singletonList(seriesA); // Lista com Série A
        List<Series> seriesListB = Collections.singletonList(seriesB); // Lista com Série B
        List<Series> seriesListAB = Arrays.asList(seriesA, seriesB); // Lista com Séries A e B

        assertEquals(seriesListA, seriesList1);
        assertEquals(seriesListAB, seriesList2);
        assertEquals(seriesListB, seriesList3);
        assertTrue(seriesList4.isEmpty());
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