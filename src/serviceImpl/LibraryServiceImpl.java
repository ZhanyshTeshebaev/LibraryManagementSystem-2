package serviceImpl;

import models.Database;
import models.Library;
import service.LibraryService;

import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    private final Database database;

    public LibraryServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<Library> saveLibrary(List<Library> libraries) {
        database.getLibraries().addAll(libraries);
        return libraries;
    }

    @Override
    public List<Library> getAllLibraries() {
        return database.getLibraries();
    }

    @Override
    public Library getLibraryById(Long id) {
        for (Library library : database.getLibraries()) {
            if (library.getId().equals(id)) {
                return library;
            }
        }
        return null;
    }

    @Override
    public Library updateLibrary(Long id, Library updatedLibrary) {
        Library library = getLibraryById(id);

        if (library == null) {
            return null;
        }

        library.setName(updatedLibrary.getName());
        library.setAddress(updatedLibrary.getAddress());

        return library;
    }

    @Override
    public String deleteLibrary(Long id) {
        Library library = getLibraryById(id);

        if (library == null) {
            return "Library not found!";
        }

        database.getLibraries().remove(library);
        database.getBooks().removeAll(library.getBooks());
        database.getReaders().removeAll(library.getReaders());

        return "Library successfully deleted!";
    }
}