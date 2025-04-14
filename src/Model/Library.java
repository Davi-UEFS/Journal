package Model;

import java.util.ArrayList;
import java.util.TreeSet;

import Model.Medias.Book;
import Model.Medias.Movie;
import Model.Medias.Series;

import Model.Exceptions.MediaAlreadyExistsException;


public class Library{
    private final ArrayList<Book> bookList;
    private final ArrayList<Movie> movieList;
    private final ArrayList<Series> seriesList;
    private final TreeSet<Integer> yearsRegistered;

    public Library(){
        this.bookList = new ArrayList<>();
        this.movieList = new ArrayList<>();
        this.seriesList = new ArrayList<>();
        this.yearsRegistered = new TreeSet<>();

    }

    public void exists(Book book) throws MediaAlreadyExistsException {
        for(Book bookE: bookList){
            if(book.getId() == bookE.getId())
                throw new MediaAlreadyExistsException("Esse livro já foi cadastrado!");

        }
    }

    public void exists(Movie movie) throws MediaAlreadyExistsException {
        for(Movie movieE: movieList){
            if(movie.getId() == movieE.getId())
                throw new MediaAlreadyExistsException("Esse filme já foi cadastrado!");

        }
    }

    public void exists(Series series) throws MediaAlreadyExistsException {
        for(Series seriesE: seriesList){
            if(series.getId() == seriesE.getId())
                throw new MediaAlreadyExistsException("Essa série já foi cadastrada!");

        }
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

    public void addYear(int year){
        yearsRegistered.add(year);
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

    public TreeSet<Integer> getYearsRegistered() {
        return yearsRegistered;
    }
}


