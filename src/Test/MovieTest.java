package Test;

import Controller.MovieService;
import Model.Genres;
import Model.Library;

import Model.Media.Movie;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;

public class MovieTest {

    @Test
    public void addMovieTest() {
        Library journal = new Library();
        MovieService movieService = new MovieService(journal);

        String result1 = movieService.register(
                "Aventuras no Espaço", 2021, Genres.AVENTURA, new String[]{"Carlos", "Mariana", "João"},
                Duration.ofMinutes(125), "Maria Silva", "Roteiro etc etc",
                "Space Adventures", new String[]{"Netflix", "HBO Max"}
        );

        String result2 = movieService.register(
                "Mistério na Floresta", 2022, Genres.COMÉDIA, new String[]{"Lucas", "Clara", "Paulo"},
                Duration.ofMinutes(98), "Fernanda Costa", "Roteiro etc etc",
                "Forest Mystery", new String[]{"Amazon Prime", "GloboPlay"}
        );

        assertEquals("Filme registrado com sucesso!", result1);
        assertEquals("Filme registrado com sucesso!", result2);
        assertEquals(2, movieService.getAllMovies().size());

        printMovieList(movieService.getAllMovies());
    }

    @Test
    public void testRateAndReviewMovie() {
        Library journal = new Library();
        MovieService movieService = new MovieService(journal);

        movieService.register(
                "Aventuras no Espaço", 2021, Genres.AVENTURA, new String[]{"Carlos", "Mariana", "João"},
                Duration.ofMinutes(125), "Maria Silva", "Roteiro etc etc",
                "Space Adventures", new String[]{"Netflix", "HBO Max"}
        );

        // Testar avaliação
        String ratingResult = movieService.rate("Aventuras no Espaço", 4.5);
        assertEquals("Avaliação salva com sucesso", ratingResult);

        // Testar review
        String reviewResult = movieService.writeReview("Aventuras no Espaço", "Ótimo filme!");
        assertEquals("Review salva com sucesso", reviewResult);

        // Verificar avaliação e review
        System.out.println(movieService.showRating("Aventuras no Espaço"));
        System.out.println(movieService.readReview("Aventuras no Espaço"));
    }

    private void printMovieList(ArrayList<Movie> movieList){
        movieList.forEach(movie -> System.out.println(movie.toString()));
    }
}
