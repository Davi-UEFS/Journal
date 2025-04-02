package Journal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Media.*;

public class JournalModel {
    private final ArrayList<Book> bookList;
    private final ArrayList<Movie> movieList;
    private final ArrayList<Series> seriesList;
    private final ArrayList<String> genresList;

    public JournalModel(){
        this.bookList = new ArrayList<>();
        this.movieList = new ArrayList<>();
        this.seriesList = new ArrayList<>();
        this.genresList = new ArrayList<>(Arrays.asList("Terror", "Suspense",
                "Romance", "Aventura", "Outros"));
    }

    public void addBook(Book book){
        bookList.add(book);
    }

    public void addMovie(Movie movie){
        movieList.add(movie);
    }

    public void addSeries(Series series){
        seriesList.add(series);
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public ArrayList<Series> getSeriesList() {
        return seriesList;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public ArrayList<String> getGenresList() {
        return genresList;
    }
}
