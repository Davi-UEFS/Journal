package Model.Medias;
import Model.Genres;

public class Book extends Media{
    private final String isbn;
    private final String author;
    private final String publisher;
    private boolean owned;
    private String seenDate;

    public Book(String title, int year, Genres genre, String isbn, String author, String publisher, boolean owned) {
        super(title, year, genre);
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.owned = owned;
        this.seenDate = null;
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

    public String getSeenDate() {
        return seenDate;
    }

    public void setSeenDate(String readDate) {
        this.seenDate = readDate;
    }

    @Override
    public int getId(){
        return title.hashCode() + isbn.hashCode() + 227*year;
    }

    @Override
    public String getMediaType(){
        return "Livro";
    }

    @Override
    public String toString() {
        String string = "\n" + title + " (" + this.year + ")\nAutor: " + author + "\nEditora: " + publisher + "\nISBN: " + isbn;
        if(seenDate != null){
            string += "\nVisto em: " + seenDate;
        }
        return string;
    }

}
