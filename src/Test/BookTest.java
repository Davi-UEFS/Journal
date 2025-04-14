package Test;

import Controller.BookService;
import Model.Genres;
import Model.Library;
import Model.Medias.Book;
import Model.Medias.Media;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookTest {


    @Test
    public void testAddBook(){
        Library journal = new Library();
        BookService bookService = new BookService(journal);

        System.out.println(bookService.register("Alpha", 2025, Genres.AÇÃO,"12345",
                "Davi", "PBL Books", true));

        System.out.println(bookService.register("Alpha", 2025, Genres.FICÇÃO,"12345",
                "Davi", "PBL Books", true));

        assert(journal.getBookList().getFirst().getTitle().equals("Alpha"));

        printAllBooks(bookService);
    }

    @Test
    public void testSearchBook(){
        Library journal = new Library();
        BookService bookService = new BookService(journal);


        bookService.register("Alpha", 1999, Genres.ROMANCE, "978-3161484100",
                "Carlos Drummond", "PBL Books", true);

        bookService.register("Beta", 2015, Genres.AÇÃO, "978-0451524935",
                "Clarice Lispector", "PBL Books", false);

        bookService.register("Gamma", 1980, Genres.MISTÉRIO, "978-0553103540",
                "Jorge Amado", "PBL Books", true);

        bookService.register("Delta", 2022, Genres.OUTROS, "978-0743273565",
                "Machado de Assis", "PBL Books", false);

        System.out.println("Livros com 'ta': ");
        List<Book> books = bookService.searchByTitle("ta", bookService.getAllBooks());
        printBookList(books);
        System.out.println("Livros com 'Al");
        books = bookService.searchByTitle("Al", bookService.getAllBooks());
        printBookList(books);

        System.out.println("Por genero crescente");
        System.out.println(bookService.mapByGenreRate(bookService.getAllBooks(), true));

    }

    @Test
    public void checkHash(){
        Library journal = new Library();
        BookService bookService = new BookService(journal);

        bookService.register("Alpha", 1999, Genres.FICÇÃO, "978-3161484100",
                "Carlos Drummond", "PBL Books", true);

        System.out.println(journal.getBookList().getFirst().getId());
    }

    @Test
    public void testBooksByGenre(){
        Library journal = new Library();
        BookService bookService = new BookService(journal);


        bookService.register("Alpha", 1999, Genres.ROMANCE, "978-3161484100",
                "Carlos Drummond", "PBL Books", true);

        bookService.register("Beta", 2015, Genres.AÇÃO, "978-0451524935",
                "Clarice Lispector", "PBL Books", false);

        bookService.register("Gamma", 1980, Genres.MISTÉRIO, "978-0553103540",
                "Jorge Amado", "PBL Books", true);

        bookService.register("Delta", 2022, Genres.OUTROS, "978-0743273565",
                "Machado de Assis", "PBL Books", false);


        System.out.println("Por genero crescente");
        printMapGenreMedia(bookService.mapByGenreRate(bookService.getAllBooks(),true));

    }

    private void printAllBooks(BookService bookService){

        ArrayList<Book> bookList = bookService.getAllBooks();
        for(Book book: bookList)
            System.out.printf("Titulo: %s (%d) \n ", book.getTitle(), book.getYear());

    }

    private void printBookList(List<Book> bookList) {
        for(Book book: bookList)
            System.out.printf("Titulo: %s (%d)\n", book.getTitle(), book.getYear());

    }

    private <T extends Media> void printMapGenreMedia(Map<Genres, List<T>> mapGenreMedia) {
        for (Map.Entry<Genres, List<T>> thisGenreMedia : mapGenreMedia.entrySet()) {

            if (!thisGenreMedia.getValue().isEmpty()) {
                System.out.println(thisGenreMedia.getKey());
                for (Media media : thisGenreMedia.getValue())
                    System.out.println(media + "\n");
            }
        }
    }

    private <T extends Media> void printMapYearMedia(Map<Integer, List<T>> mapYearMedia) {
        for (Map.Entry<Integer, List<T>> thisYearMedia : mapYearMedia.entrySet()) {

            if (!thisYearMedia.getValue().isEmpty()) {
                System.out.println(thisYearMedia.getKey());
                for (Media media : thisYearMedia.getValue())
                    System.out.println(media + "\n");
            }
        }
    }
}
