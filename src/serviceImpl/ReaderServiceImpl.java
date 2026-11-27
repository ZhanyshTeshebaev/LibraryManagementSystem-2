package serviceImpl;

import models.Database;
import models.Library;
import models.Reader;
import service.ReaderService;

public class ReaderServiceImpl implements ReaderService {

    private final Database database;

    public ReaderServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public Reader saveReader(Reader reader) {
        database.getReaders().add(reader);
        return reader;
    }

    @Override
    public java.util.List<Reader> getAllReaders() {
        return database.getReaders();
    }

    @Override
    public Reader getReaderById(Long id) {
        for (Reader reader : database.getReaders()) {
            if (reader.getId().equals(id)) {
                return reader;
            }
        }
        return null;
    }

    @Override
    public Reader updateReader(Long id, Reader updatedReader) {
        Reader reader = getReaderById(id);

        if (reader == null) {
            return null;
        }

        reader.setFullName(updatedReader.getFullName());
        reader.setEmail(updatedReader.getEmail());
        reader.setPhoneNumber(updatedReader.getPhoneNumber());
        reader.setGender(updatedReader.getGender());

        return reader;
    }

    @Override
    public String assignReaderToLibrary(Long readerId, Long libraryId) {
        Reader reader = getReaderById(readerId);
        Library library = null;

        for (Library item : database.getLibraries()) {
            if (item.getId().equals(libraryId)) {
                library = item;
                break;
            }
        }

        if (reader == null) {
            return "Reader not found!";
        }

        if (library == null) {
            return "Library not found!";
        }

        if (library.getReaders().contains(reader)) {
            return "Reader is already assigned to this library!";
        }

        library.getReaders().add(reader);

        return "Reader successfully assigned!";
    }
}