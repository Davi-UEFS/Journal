package Journal;

import java.util.ArrayList;
import java.util.Scanner;
import Media.*;

public class JournalModel {
    private ArrayList<Book> bookList;
    private ArrayList<Movie> movieList;
    private ArrayList<Series> seriesList;
    private Scanner scanner = new Scanner(System.in);

    public JournalModel(){
        this.bookList = new ArrayList<>();
        this.movieList = new ArrayList<>();
        this.seriesList = new ArrayList<>();
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
}
