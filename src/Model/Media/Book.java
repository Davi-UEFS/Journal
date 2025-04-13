package Model.Media;
import Model.Genres;

import java.time.LocalDate;

public class Book extends Media{
    private final String isbn;
    private final String author;
    private final String publisher;
    private boolean owned;
    private LocalDate readDate;

    public Book(String title, int year, Genres genre, String isbn, String author, String publisher, boolean owned) {
        super(title, year, genre);
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.owned = owned;
    }

    
    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
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

    @Override
    public int getId(){
        return title.hashCode() + isbn.hashCode() + 227*year;
    }

    @Override
    public String toString() {
        return "\n" + title + " (" + this.year + ")\nAutor: " + author + "\nEditora: " + publisher + "\nISBN: " + isbn;
    }

}
