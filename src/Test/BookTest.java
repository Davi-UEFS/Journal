package Test;

import Model.Library;
import Controller.JournalController;
import Model.Media.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BookTest {


    @Test
    public void testAddBook(){
        Library journal = new Library();
        JournalController journalController = new JournalController(journal);
        System.out.println(journalController.register("Alpha", 2025, 4,"12345",
                "Davi", "PBL Books", true));

        System.out.println(journalController.register("Alpha", 2025, 4,"12345",
                "Davi", "PBL Books", true));

        assert(journal.getBookList().getFirst().getTitle().equals("Alpha"));

        printAllBooks(journalController);
    }

    @Test
    public void testSearchBook(){
        Library journal = new Library();
        JournalController journalController = new JournalController(journal);


        journalController.register("Alpha", 1999, 5, "978-3161484100",
                "Carlos Drummond", "PBL Books", true);

        journalController.register("Beta", 2015, 2, "978-0451524935",
                "Clarice Lispector", "PBL Books", false);

        journalController.register("Gamma", 1980, 9, "978-0553103540",
                "Jorge Amado", "PBL Books", true);

        journalController.register("Delta", 2022, 11, "978-0743273565",
                "Machado de Assis", "PBL Books", false);

        System.out.println("Livros com 'ta': ");
        List<Book> books = journalController.searchBook("title", "ta");
        printBookList(books);
        System.out.println("Livros com 'Al");
        books = journalController.searchBook("title", "Al");
        printBookList(books);

    }

    @Test
    public void checkHash(){
        Library journal = new Library();
        JournalController journalController = new JournalController(journal);

        journalController.register("Alpha", 1999, 5, "978-3161484100",
                "Carlos Drummond", "PBL Books", true);

        System.out.println(journal.getBookList().getFirst().getHashCode());
    }

    private void printAllBooks(JournalController journalController){

        ArrayList<Book> bookList = journalController.allBooks();
        for(Book book: bookList)
            System.out.printf("Titulo: %s (%d) \n ", book.getTitle(), book.getYear());

    }

    private void printBookList(List<Book> bookList) {
        for(Book book: bookList)
            System.out.printf("Titulo: %s (%d)\n", book.getTitle(), book.getYear());

    }
}
