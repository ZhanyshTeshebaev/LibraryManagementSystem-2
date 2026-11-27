import enums.Gender;
import enums.Genre;
import models.Book;
import models.Database;
import models.Library;
import models.Reader;
import service.BookService;
import service.LibraryService;
import service.ReaderService;
import serviceImpl.BookServiceImpl;
import serviceImpl.LibraryServiceImpl;
import serviceImpl.ReaderServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Database database = new Database(
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        LibraryService libraryService =
                new LibraryServiceImpl(database);

        BookService bookService =
                new BookServiceImpl(database);

        ReaderService readerService =
                new ReaderServiceImpl(database);

        while (true) {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Save Libraries");
            System.out.println("2. Get All Libraries");
            System.out.println("3. Get Library By ID");
            System.out.println("4. Update Library");
            System.out.println("5. Delete Library");
            System.out.println("6. Save Book");
            System.out.println("7. Get All Books");
            System.out.println("8. Get Book By ID");
            System.out.println("9. Delete Book");
            System.out.println("10. Clear Books By Library ID");
            System.out.println("11. Save Reader");
            System.out.println("12. Get All Readers");
            System.out.println("13. Get Reader By ID");
            System.out.println("14. Update Reader");
            System.out.println("15. Assign Reader To Library");
            System.out.println("0. Exit");

            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> saveLibraries(libraryService);
                case 2 -> getAllLibraries(libraryService);
                case 3 -> getLibraryById(libraryService);
                case 4 -> updateLibrary(libraryService);
                case 5 -> deleteLibrary(libraryService);
                case 6 -> saveBook(bookService);
                case 7 -> getAllBooks(bookService);
                case 8 -> getBookById(bookService);
                case 9 -> deleteBook(bookService);
                case 10 -> clearBooks(bookService);
                case 11 -> saveReader(readerService);
                case 12 -> getAllReaders(readerService);
                case 13 -> getReaderById(readerService);
                case 14 -> updateReader(readerService);
                case 15 -> assignReader(readerService);
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // LIBRARY METHODS

    static void saveLibraries(LibraryService service) {
        System.out.print("How many libraries? ");
        int count = scanner.nextInt();
        scanner.nextLine();

        List<Library> libraries = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            System.out.print("Library ID: ");
            Long id = scanner.nextLong();
            scanner.nextLine();

            System.out.print("Library name: ");
            String name = scanner.nextLine();

            System.out.print("Address: ");
            String address = scanner.nextLine();

            libraries.add(new Library(
                    id, name, address,
                    new ArrayList<>(),
                    new ArrayList<>()
            ));
        }

        service.saveLibrary(libraries);
        System.out.println("Libraries successfully saved!");
    }

    static void getAllLibraries(LibraryService service) {
        for (Library library : service.getAllLibraries()) {
            System.out.println(library);
        }
    }

    static void getLibraryById(LibraryService service) {
        System.out.print("Enter library ID: ");
        Long id = scanner.nextLong();

        Library library = service.getLibraryById(id);

        if (library == null) {
            System.out.println("Library not found!");
        } else {
            System.out.println(library);
        }
    }

    static void updateLibrary(LibraryService service) {
        System.out.print("Enter library ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("New name: ");
        String name = scanner.nextLine();

        System.out.print("New address: ");
        String address = scanner.nextLine();

        Library updatedLibrary = new Library(
                id, name, address,
                new ArrayList<>(),
                new ArrayList<>()
        );

        Library result = service.updateLibrary(id, updatedLibrary);

        System.out.println(
                result == null ? "Library not found!" : "Library updated!"
        );
    }

    static void deleteLibrary(LibraryService service) {
        System.out.print("Enter library ID: ");
        Long id = scanner.nextLong();

        System.out.println(service.deleteLibrary(id));
    }

    // BOOK METHODS

    static void saveBook(BookService service) {
        System.out.print("Library ID: ");
        Long libraryId = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Book ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Book name: ");
        String name = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.println("Genres: FICTION, FANTASY, DETECTIVE, HORROR, ROMANCE, HISTORY");
        System.out.print("Genre: ");
        Genre genre = Genre.valueOf(scanner.nextLine().toUpperCase());

        Book book = new Book(id, name, author, genre);

        Book result = service.saveBook(libraryId, book);

        System.out.println(
                result == null ? "Library not found!" : "Book saved!"
        );
    }

    static void getAllBooks(BookService service) {
        System.out.print("Library ID: ");
        Long libraryId = scanner.nextLong();

        List<Book> books = service.getAllBooks(libraryId);

        if (books == null) {
            System.out.println("Library not found!");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    static void getBookById(BookService service) {
        System.out.print("Library ID: ");
        Long libraryId = scanner.nextLong();

        System.out.print("Book ID: ");
        Long bookId = scanner.nextLong();

        Book book = service.getBookById(libraryId, bookId);

        System.out.println(
                book == null ? "Book not found!" : book
        );
    }

    static void deleteBook(BookService service) {
        System.out.print("Library ID: ");
        Long libraryId = scanner.nextLong();

        System.out.print("Book ID: ");
        Long bookId = scanner.nextLong();

        System.out.println(service.deleteBook(libraryId, bookId));
    }

    static void clearBooks(BookService service) {
        System.out.print("Library ID: ");
        Long libraryId = scanner.nextLong();

        service.clearBooksByLibraryId(libraryId);
    }

    // READER METHODS

    static void saveReader(ReaderService service) {
        System.out.print("Reader ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Gender (MALE/FEMALE): ");
        Gender gender = Gender.valueOf(
                scanner.nextLine().toUpperCase()
        );

        Reader reader = new Reader(
                id, fullName, email, phone, gender
        );

        service.saveReader(reader);
        System.out.println("Reader saved!");
    }

    static void getAllReaders(ReaderService service) {
        for (Reader reader : service.getAllReaders()) {
            System.out.println(reader);
        }
    }

    static void getReaderById(ReaderService service) {
        System.out.print("Reader ID: ");
        Long id = scanner.nextLong();

        Reader reader = service.getReaderById(id);

        System.out.println(
                reader == null ? "Reader not found!" : reader
        );
    }

    static void updateReader(ReaderService service) {
        System.out.print("Reader ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("New full name: ");
        String fullName = scanner.nextLine();

        System.out.print("New email: ");
        String email = scanner.nextLine();

        System.out.print("New phone number: ");
        String phone = scanner.nextLine();

        System.out.print("New gender (MALE/FEMALE): ");
        Gender gender = Gender.valueOf(
                scanner.nextLine().toUpperCase()
        );

        Reader updatedReader = new Reader(
                id, fullName, email, phone, gender
        );

        Reader result = service.updateReader(id, updatedReader);

        System.out.println(
                result == null ? "Reader not found!" : "Reader updated!"
        );
    }

    static void assignReader(ReaderService service) {
        System.out.print("Reader ID: ");
        Long readerId = scanner.nextLong();

        System.out.print("Library ID: ");
        Long libraryId = scanner.nextLong();

        System.out.println(
                service.assignReaderToLibrary(readerId, libraryId)
        );
    }
}