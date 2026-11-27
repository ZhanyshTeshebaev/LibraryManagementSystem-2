package service;

import models.Reader;
import java.util.List;

public interface ReaderService {
    Reader saveReader(Reader reader);

    List<Reader> getAllReaders();

    Reader getReaderById(Long id);

    Reader updateReader(Long id, Reader reader);

    String assignReaderToLibrary(Long readerId, Long libraryId);
}