package Test;

import Controller.BookService;
import Model.Enums.Genres;
import Model.Library;
import Model.Medias.Book;
import Model.Medias.Media;
import Model.Enums.Months;
import Model.Result.Failure;
import Model.Result.IResult;

import Model.Result.Success;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookTest {
    Library journal;
    BookService bookService;

    @BeforeEach void setUp() {
        journal = new Library();
        bookService = new BookService(journal);
    }

    @Test
    public void testAddBook() {
        IResult result1 = bookService.register("Alpha", 2025, Genres.AÇÃO, "12345", "Davi", "PBL Books", true);
        System.out.println(result1.getMessage());

        IResult result2 = bookService.register("Alpha", 2025, Genres.FICÇÃO, "12345", "Davi", "PBL Books", true);
        System.out.println(result2.getMessage());

        assertEquals("Alpha", journal.getBookList().getFirst().getTitle());
        printAllBooks(bookService);
    }

    @Test
    public void testSearchBook() {

        bookService.register("Alpha", 1999, Genres.ROMANCE, "978-3161484100", "Carlos Drummond", "PBL Books", true);
        bookService.register("Beta", 2015, Genres.AÇÃO, "978-0451524935", "Clarice Lispector", "PBL Books", false);
        bookService.register("Gamma", 1980, Genres.MISTÉRIO, "978-0553103540", "Jorge Amado", "PBL Books", true);
        bookService.register("Delta", 2022, Genres.OUTROS, "978-0743273565", "Machado de Assis", "PBL Books", false);

        System.out.println("Livros com 'ta': ");
        List<Book> books = bookService.searchByTitle("ta", bookService.getAllBooks());
        assertEquals(2, books.size());
        printBookList(books);

        System.out.println("Livros com 'Al'");
        books = bookService.searchByTitle("Al", bookService.getAllBooks());
        assertEquals(1, books.size());
        printBookList(books);

        System.out.println("Por genero crescente");
        System.out.println(bookService.mapByGenreRate(bookService.getAllBooks(), true));
    }

    @Test
    public void checkHash() {

        IResult result = bookService.register("Alpha", 1999, Genres.FICÇÃO, "978-3161484100", "Carlos Drummond", "PBL Books", true);
        System.out.println(result.getMessage());
        System.out.println(journal.getBookList().getFirst().getId());
    }

    @Test
    public void testBooksByGenre() {

        bookService.register("Alpha", 1999, Genres.ROMANCE, "978-3161484100", "Carlos Drummond", "PBL Books", true);
        bookService.register("Beta", 2015, Genres.AÇÃO, "978-0451524935", "Clarice Lispector", "PBL Books", false);
        bookService.register("Gamma", 1980, Genres.MISTÉRIO, "978-0553103540", "Jorge Amado", "PBL Books", true);
        bookService.register("Delta", 2022, Genres.OUTROS, "978-0743273565", "Machado de Assis", "PBL Books", false);

        System.out.println("Por genero crescente");
        printMapGenreMedia(bookService.mapByGenreRate(bookService.getAllBooks(), true));
    }

    @Test
    public void testBookRating() {

        bookService.register(
                "Alpha", 2000, Genres.OUTROS,
                "123", "Gui", "Omega", false);

        Book book = bookService.getAllBooks().getFirst();
        IResult result1 = bookService.rate(book, 3.5);

        // Falha: Não foi marcado como visto
        assertEquals(Failure.class, result1.getClass());

        bookService.markAsSeen(book, 2005, Months.AGOSTO);
        IResult result2 = bookService.rate(book, 0);
        IResult result3 = bookService.rate(book, 5.1);

        // Ambos falham: Fora do limite
        assertEquals(Failure.class, result2.getClass());
        assertEquals(Failure.class, result3.getClass());

        IResult result4 = bookService.rate(book, 2.5);

        // Sucesso
        assertEquals(Success.class, result4.getClass());
        assertEquals(2.5, book.getRating());

    }

    @Test
    public void testBookSeenDate() {
        bookService.register(
                "Alpha", 2000, Genres.OUTROS,
                "123", "Gui", "Omega", false);

        Book book = bookService.getAllBooks().getFirst();

        IResult result1 = bookService.markAsSeen(book, 1999, Months.AGOSTO);
        IResult result2 = bookService.markAsSeen(book, 2026, Months.AGOSTO);

        // Ambos falham: Anos inválidos
        assertEquals(Failure.class, result1.getClass());
        assertEquals(Failure.class, result2.getClass());

        IResult result5 = bookService.markAsSeen(book, 2015, Months.AGOSTO);

        // Sucesso
        assertEquals(Success.class, result5.getClass());
        System.out.println(book.getSeenDate());
        // Output: AGOSTO de 2015

    }

    @Test
    public void testBookPrint(){
        bookService.register("Alpha", 1999, Genres.ROMANCE, "978-3161484100", "Carlos Drummond", "PBL Books", true);
        bookService.register("Beta", 2015, Genres.AÇÃO, "978-0451524935", "Clarice Lispector", "PBL Books", false);
        bookService.register("Gamma", 1980, Genres.MISTÉRIO, "978-0553103540", "Jorge Amado", "PBL Books", true);
        bookService.register("Delta", 2022, Genres.OUTROS, "978-0743273565", "Machado de Assis", "PBL Books", false);

        List<Book> books = bookService.getAllBooks();
        Book book1 = books.get(0);
        Book book2 = books.get(1);
        Book book3 = books.get(2);

        bookService.markAsSeen(book1, 2005, Months.AGOSTO);
        bookService.markAsSeen(book2, 2016, Months.SETEMBRO);
        bookService.markAsSeen(book3, 1999, Months.MARÇO);

        bookService.rate(book1, 3.8);
        bookService.rate(book2, 2.2);

        books = bookService.sortDescending(books);

        for(Book i: books)
            System.out.println(i.toString());
    }

    private void printAllBooks(BookService bookService) {
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