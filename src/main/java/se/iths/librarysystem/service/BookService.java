package se.iths.librarysystem.service;

import org.springframework.stereotype.Service;
import se.iths.librarysystem.entity.BookEntity;
import se.iths.librarysystem.repository.BookRepository;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookEntity createBook(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    public Iterable<BookEntity> getBooksByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Optional<BookEntity> findById(Long id) {
        return bookRepository.findById(id);
    }
}
