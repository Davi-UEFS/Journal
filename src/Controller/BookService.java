package Controller;

import Model.Exceptions.MediaAlreadyExistsException;
import Model.Genres;
import Model.Library;
import Model.Medias.Book;
import Model.Result.*;

import java.util.ArrayList;
import java.util.List;

public class BookService extends CommonService<Book>{

    public BookService(Library journal){
        super(journal);
    }

    public IResult register(String name, int year, Genres genre, String isbn,
                            String author, String publisher, boolean owned) {

        Book book = new Book(name, year, genre, isbn, author, publisher, owned);

        try {
            journal.exists(book);
            journal.addBook(book);
            journal.addYear(year);
            return new Success("Livro", "Registrado com sucesso!");
        } catch (MediaAlreadyExistsException e){
            return new Failure("Livro", e.getMessage());
        }
    }

    public List<Book> searchBookByIsbn(String isbn){
        String isbnLower = isbn.toLowerCase().trim();
        List<Book> filteredBooks = journal.getBookList().stream().filter
                (book -> book.getIsbn().toLowerCase().contains(isbnLower)).toList();

        return sortAscending(filteredBooks);
    }

    public List<Book> searchBookByAuthor(String author){
        String authorLower = author.toLowerCase().trim();
        List<Book> filteredBooks = journal.getBookList().stream().filter
                (book -> book.getAuthor().toLowerCase().contains(authorLower)).toList();

        return sortAscending(filteredBooks);
    }

    public ArrayList<Book> getAllBooks(){
        return journal.getBookList();
    }
}
