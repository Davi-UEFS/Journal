package Test;

import Controller.MovieService;
import Model.Genres;
import Model.Library;

import Model.Medias.Book;
import Model.Medias.Movie;
import Model.Months;
import Model.Result.Failure;
import Model.Result.IResult;
import Model.Result.Success;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MovieTest {
    Library journal;
    MovieService movieService;
    @BeforeEach
    void setUp() {
        journal = new Library();
        movieService = new MovieService(journal);
    }

    @Test
    public void addMovieTest() {

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

        movieService.register(
                "Aventuras no Espaço", 2021, Genres.AVENTURA, new String[]{"Carlos", "Mariana", "João"},
                Duration.ofMinutes(125), "Maria Silva", "Roteiro etc etc",
                "Space Adventures", new String[]{"Netflix", "HBO Max"}
        );

        Movie testMovie = journal.getMovieList().getFirst();
        movieService.markAsSeen(testMovie, 2023, Months.SETEMBRO);
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

    @Test
    public void testMovieRating() {

        movieService.register(
                "Alpha", 2000, Genres.OUTROS, new String[]{"One, Two"},
                Duration.ofMinutes(125), "Omega", "Roteiro",
                "Omega", new String[]{"One, Two"});

        Movie movie = movieService.getAllMovies().getFirst();
        IResult result1 = movieService.rate(movie, 3.5);

        // Falha: Não foi marcado como visto
        assertEquals(Failure.class, result1.getClass());

        movieService.markAsSeen(movie, 2003, Months.SETEMBRO);
        IResult result2 = movieService.rate(movie, 0);
        IResult result3 = movieService.rate(movie, 5.1);

        // Ambos falham: Fora do limite
        assertEquals(Failure.class, result2.getClass());
        assertEquals(Failure.class, result3.getClass());

        IResult result4 = movieService.rate(movie, 2.5);

        // Sucesso
        assertEquals(Success.class, result4.getClass());
        assertEquals(2.5, movie.getRating());

    }

    @Test
    public void testMoviePrint(){
        movieService.register(
                "Alpha", 2000, Genres.OUTROS, new String[]{"One, Two"},
                Duration.ofMinutes(125), "Plato", "Roteiro",
                "Omega", new String[]{"One, Two"});
        movieService.register(
                "Beta", 1999, Genres.ROMANCE, new String[]{"One, Two, Three"},
                Duration.ofMinutes(160), "Socrates", "Roteiro",
                "Phi", new String[]{"One"});
        movieService.register(
                "Gamma", 2011, Genres.TERROR, new String[]{"One, Three"},
                Duration.ofMinutes(125), "Aristotle", "Roteiro",
                "Theta", new String[]{"Two"});
        movieService.register(
                "Delta", 2001, Genres.AÇÃO, new String[]{"Two, Three"},
                Duration.ofMinutes(200), "Caesar", "Roteiro",
                "Rho", new String[]{"Three"});

        List<Movie> movies = movieService.getAllMovies();
        Movie movie1 = movies.get(0);
        Movie movie2 = movies.get(1);
        Movie movie3 = movies.get(2);

        movieService.markAsSeen(movie1, 2005, Months.AGOSTO);
        movieService.markAsSeen(movie2, 2016, Months.SETEMBRO);
        movieService.markAsSeen(movie3, 2012, Months.MARÇO);

        movieService.rate(movie1, 3.8);
        movieService.rate(movie2, 2.2);

        movies = movieService.sortDescending(movies);

        for(Movie i: movies)
            System.out.println(i.toString());
    }

    private void printMovieList(ArrayList<Movie> movieList){
        movieList.forEach(movie -> System.out.println(movie.toString()));
    }
}
