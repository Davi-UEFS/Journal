package Model.Media;
import Model.Genres;

import java.time.LocalDate;

public class Book extends Media{
    private final String isbn;
    private final String author;
    private final String publisher;
    private boolean owned;
    private LocalDate readDate;

    public Book(String name, int year, Genres genre, String isbn, String author, String publisher, boolean owned) {
        super(name, year, genre);
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.owned = owned;
        this.hashCode = hashCodeMaker(name, isbn);
    }

    private int hashCodeMaker(String name, String isbn){
        return name.hashCode() + isbn.hashCode();
    }
    
    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public LocalDate getReadDate() {
        return readDate;
    }
    public void setReadDate(LocalDate readDate) {
        this.readDate = readDate;
    }

    public String toString() {
        return "\n" + title + " (" + this.year + ")\nAutor: " + author + "\nEditora: " + publisher + "\nISBN: " + isbn;
    }

}
