package se.iths.librarysystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import se.iths.librarysystem.dto.Book;
import se.iths.librarysystem.entity.BookEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public List<Book> getAllBooks() {
        Iterable<BookEntity> bookEntities = bookRepository.findAll();
        List<BookEntity> bookEntityList = new ArrayList<>();
        bookEntities.forEach(bookEntityList::add);
        return bookEntityList.stream().map(book -> modelMapper.map(book, Book.class)).toList();
    }

    public Book createBook(Book book) {
        BookEntity bookEntity = modelMapper.map(book, BookEntity.class);
        BookEntity savedBook = bookRepository.save(bookEntity);
        return modelMapper.map(savedBook, Book.class);
    }

    public List<Book> getBooksByIsbn(String isbn) {
        List<BookEntity> books = bookRepository.findByIsbn(isbn);
        return books.stream().map(book -> modelMapper.map(book, Book.class)).toList();
    }
    public List<BookEntity> getBookEntityByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Optional<BookEntity> findById(Long id) {
        return bookRepository.findById(id);
    }

    public void updateBook(BookEntity book) {
        bookRepository.save(book);
    }

    public void deleteBook(Long id){
        BookEntity foundBook = bookRepository.findById(id).orElseThrow(() -> new IdNotFoundException("book", id));
        bookRepository.deleteById(foundBook.getId());
    }

}
