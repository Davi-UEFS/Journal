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
        journalController.registerBook("Alpha", 2025, "Scifi", "12345",
                "Davi", "PBL Books", true);

        printAllBooks(journalController);
    }

    private void printAllBooks(JournalController journalController){

        ArrayList<Book> bookList = journalController.allBooks();
        for(Book book: bookList)
            System.out.printf("Titulo: %s (%d) \n ", book.getTitle(), book.getYear());

    }
}
