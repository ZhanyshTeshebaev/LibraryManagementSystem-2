package serviceImpl;

import models.Book;
import models.Database;
import models.Library;
import service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final Database database;

    public BookServiceImpl(Database database) {
        this.database = database;
    }

    private Library findLibrary(Long libraryId) {
        for (Library library : database.getLibraries()) {
            if (library.getId().equals(libraryId)) {
                return library;
            }
        }
        return null;
    }

    @Override
    public Book saveBook(Long libraryId, Book book) {
        Library library = findLibrary(libraryId);

        if (library == null) {
            return null;
        }

        library.getBooks().add(book);
        database.getBooks().add(book);

        return book;
    }

    @Override
    public List<Book> getAllBooks(Long libraryId) {
        Library library = findLibrary(libraryId);

        if (library == null) {
            return null;
        }

        return library.getBooks();
    }

    @Override
    public Book getBookById(Long libraryId, Long bookId) {
        Library library = findLibrary(libraryId);

        if (library == null) {
            return null;
        }

        for (Book book : library.getBooks()) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }

        return null;
    }

    @Override
    public String deleteBook(Long libraryId, Long bookId) {
        Library library = findLibrary(libraryId);

        if (library == null) {
            return "Library not found!";
        }

        Book book = getBookById(libraryId, bookId);

        if (book == null) {
            return "Book not found!";
        }

        library.getBooks().remove(book);
        database.getBooks().remove(book);

        return "Book successfully deleted!";
    }

    @Override
    public void clearBooksByLibraryId(Long libraryId) {
        Library library = findLibrary(libraryId);

        if (library == null) {
            System.out.println("Library not found!");
            return;
        }

        database.getBooks().removeAll(library.getBooks());
        library.getBooks().clear();

        System.out.println("All books successfully cleared!");
    }
}