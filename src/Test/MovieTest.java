package Test;

import Controller.JournalController;
import Model.Genres;
import Model.Library;

import Model.Media.Movie;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;

public class MovieTest {

    @Test
    public void addMovieTest(){
        Library journal = new Library();
        JournalController journalController = new JournalController(journal);

        journalController.register(
                "Aventuras no Espaço", 2021, Genres.AVENTURA, new String[]{"Carlos", "Mariana", "João"},
                Duration.ofMinutes(125), "Maria Silva", "Roteiro etc etc",
                "Space Adventures", new String[]{"Netflix", "HBO Max"}
        );

        journalController.register(
                "Mistério na Floresta", 2022, Genres.COMÉDIA, new String[]{"Lucas", "Clara", "Paulo"},
                Duration.ofMinutes(98), "Fernanda Costa", "Roteiro etc etc",
                "Forest Mystery", new String[]{"Amazon Prime", "GloboPlay"}
        );

        assertEquals(2, journalController.allMovies().size());

        printMovieList(journalController.allMovies());
    }

    private void printMovieList(ArrayList<Movie> movieList){
        movieList.forEach(movie -> System.out.println(movie.toString()));
    }
}
