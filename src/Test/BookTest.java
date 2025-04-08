package Test;

import Model.Library;
import Controller.JournalController;
import Model.Media.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BookTest {


    @Test
    public void testAddBook(){
        Library journal = new Library();
        JournalController journalController = new JournalController(journal);
        journalController.register("Alpha", 2025, 4,"12345",
                "Davi", "PBL Books", true);

        assert(journal.getBookList().getFirst().getTitle().equals("Alpha"));

        printAllBooks(journalController);
    }

    private void printAllBooks(JournalController journalController){

        ArrayList<Book> bookList = journalController.allBooks();
        for(Book book: bookList)
            System.out.printf("Titulo: %s (%d) \n ", book.getTitle(), book.getYear());

    }
}
