package Model;

import java.util.ArrayList;

import Model.Exceptions.MediaAlreadyExistsException;
import Model.Media.Book;
import Model.Media.Media;
import Model.Media.Movie;
import Model.Media.Series;
import Model.Exceptions.MediaNotFoundException;

public class Library{
    private final ArrayList<Book> bookList;
    private final ArrayList<Movie> movieList;
    private final ArrayList<Series> seriesList;

    public Library(){
        this.bookList = new ArrayList<>();
        this.movieList = new ArrayList<>();
        this.seriesList = new ArrayList<>();

    }

    public Book findBook(String name) throws MediaNotFoundException {
        String lowerName = name.toLowerCase();

        for(Book book:bookList) {
            if (lowerName.equals(book.getTitle().toLowerCase()))
                return book;
        }
        throw new MediaNotFoundException("Obra não encontrada");
    }

    public Movie findMovie(String name) throws MediaNotFoundException {
        String lowerName = name.toLowerCase();

        for(Movie movie:movieList) {
            if (lowerName.equals(movie.getTitle().toLowerCase()))
                return movie;
        }
        throw new MediaNotFoundException("Obra não encontrada");
    }

    public Series findSeries(String name) throws MediaNotFoundException{
        String lowerName = name.toLowerCase();

        for(Series series:seriesList) {
            if (lowerName.equals(series.getTitle().toLowerCase()))
                return series;
        }
        throw new MediaNotFoundException("Obra não encontrada");
    }

    public Media findMedia(String name) throws MediaNotFoundException {
        try {
            return findBook(name);
        } catch (MediaNotFoundException e) {
            try {
                return findMovie(name);
            } catch (MediaNotFoundException e2) {
                return findSeries(name);
            }
        }
    }

    public void isRegistered(Book book) throws MediaAlreadyExistsException {
        for(Book bookE: bookList){
            if(book.getHashCode() == bookE.getHashCode())
                throw new MediaAlreadyExistsException("Esse livro já foi cadastrado!");

        }
    }

    public void isRegistered(Movie movie) throws MediaAlreadyExistsException {
        for(Movie movieE: movieList){
            if(movie.getHashCode() == movieE.getHashCode())
                throw new MediaAlreadyExistsException("Esse filme já foi cadastrado!");

        }
    }

    public void isRegistered(Series series) throws MediaAlreadyExistsException {
        for(Series seriesE: seriesList){
            if(series.getHashCode() == seriesE.getHashCode())
                throw new MediaAlreadyExistsException("Esse livro já foi cadastrado!");

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


