package Controller;

import Model.Exceptions.MediaAlreadyExistsException;
import Model.Enums.Genres;
import Model.Library;
import Model.Medias.Book;
import Model.Enums.Months;
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

    public IResult markAsSeen(Book book,  int year, Months month){

        if(book.isSeen())
            return new Failure("Livro", "Já marcado como lido");

        if(year < book.getYear() || year > 2025)
            return new Failure("Livro", "Ano inválido!");

        String date = month.toString() + " de " + year;
        book.setSeen(true);
        book.setSeenDate(date);
        return new Success("Livro", "Data de leitura registrada.");
    }

    public ArrayList<Book> getAllBooks(){
        return journal.getBookList();
    }

}
