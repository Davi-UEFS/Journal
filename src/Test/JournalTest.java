package Test;

import Journal.JournalModel;
import Journal.JournalController;
import Media.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class JournalTest {


    @Test
    public void testAddBook(){
        JournalModel journal = new JournalModel();
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
