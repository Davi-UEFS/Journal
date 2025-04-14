package Test;

import Controller.MovieService;
import Model.Genres;
import Model.Library;

import Model.Medias.Movie;
import Model.Result.Failure;
import Model.Result.IResult;
import Model.Result.Success;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;

public class MovieTest {

    @Test
    public void addMovieTest() {
        Library journal = new Library();
        MovieService movieService = new MovieService(journal);

        IResult result1 = movieService.register(
                "Aventuras no Espaço", 2021, Genres.AVENTURA, new String[]{"Carlos", "Mariana", "João"},
                Duration.ofMinutes(125), "Maria Silva", "Roteiro etc etc",
                "Space Adventures", new String[]{"Netflix", "HBO Max"}
        );
        //Mesmo nome e ano do primeiro
        IResult result2 = movieService.register(
                "Aventuras no Espaço", 2021, Genres.COMÉDIA, new String[]{"Lucas", "Clara", "Paulo"},
                Duration.ofMinutes(98), "Fernanda Costa", "Roteiro etc etc",
                "Forest Mystery", new String[]{"Amazon Prime", "GloboPlay"}
        );

        assertEquals(Success.class, result1.getClass());
        assertEquals(Failure.class, result2.getClass());
        assertEquals(1, movieService.getAllMovies().size());

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

        Movie testMovie = journal.getMovieList().getFirst();
        movieService.markAsSeen(testMovie);
        // Testar avaliação
        IResult ratingResult = movieService.rate(testMovie, 4.5);
        assertEquals(Success.class, ratingResult.getClass());
        System.out.println(ratingResult.getMessage());

        // Testar review
        IResult reviewResult = movieService.writeReview(testMovie, "Ótimo filme!");
        assertEquals(Success.class, reviewResult.getClass());
        System.out.println(reviewResult.getMessage());

        // Verificar avaliação e review
        System.out.println(movieService.showRating(testMovie));
        System.out.println(movieService.readReview(testMovie));
    }

    private void printMovieList(ArrayList<Movie> movieList){
        movieList.forEach(movie -> System.out.println(movie.toString()));
    }
}
