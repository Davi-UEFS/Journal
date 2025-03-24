package Media;
import java.time.LocalDate;

public class Book extends Media{
    private String isbn;
    private String author;
    private String publisher;
    private boolean owned;
    private LocalDate readDate;

    public Book(String name, int year, String genre, String isbn, String author, String publisher, boolean owned) {
        super(name, year, genre);
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.owned = owned;
    }
    
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
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


}
