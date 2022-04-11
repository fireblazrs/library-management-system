package se.iths.librarysystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.iths.librarysystem.dto.Book;
import se.iths.librarysystem.entity.AuthorEntity;
import se.iths.librarysystem.entity.BookEntity;
import se.iths.librarysystem.entity.BookFormatEntity;
import se.iths.librarysystem.entity.GenreEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.repository.AuthorRepository;
import se.iths.librarysystem.repository.BookFormatRepository;
import se.iths.librarysystem.repository.BookRepository;
import se.iths.librarysystem.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final BookFormatRepository bookFormatRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper, BookFormatRepository bookFormatRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.bookFormatRepository = bookFormatRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
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

    public Book findBookById(Long id){
        BookEntity foundBook = bookRepository.findById(id).orElseThrow(() -> new IdNotFoundException("book", id));
        return modelMapper.map(foundBook, Book.class);
    }

    public void deleteBook(Long id){
        BookEntity foundBook = bookRepository.findById(id).orElseThrow(() -> new IdNotFoundException("book", id));
        bookRepository.deleteById(foundBook.getId());
    }

    @Transactional
    public Book addBookFormatToBook(Long bookId, Long bookFormatId) {
        BookEntity book = bookRepository.findById(bookId).orElseThrow(() -> new IdNotFoundException("book", bookId));
        BookFormatEntity bookFormat = bookFormatRepository.findById(bookFormatId)
                .orElseThrow(() -> new IdNotFoundException("book format", bookFormatId));
        book.addBookFormatEntity(bookFormat);
        bookFormat.addEnrolledBook(book);
        bookRepository.save(book);
        return modelMapper.map(book,Book.class);
    }

    @Transactional
    public Book addGenreToBook(Long bookId, Long genreId) {
        BookEntity book = bookRepository.findById(bookId).orElseThrow(() -> new IdNotFoundException("book", bookId));
        GenreEntity genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new IdNotFoundException("genre", genreId));
        book.setGenreEntity(genre);
        genre.addBook(book);
        bookRepository.save(book);
        return modelMapper.map(book,Book.class);
    }

    @Transactional
    public Book addAuthorToBook(Long bookId, Long authorId) {
        BookEntity book = bookRepository.findById(bookId).orElseThrow(() -> new IdNotFoundException("book", bookId));
        AuthorEntity author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IdNotFoundException("author", authorId));
        book.addAuthorEntity(author);
        author.addBookEntity(book);
        bookRepository.save(book);
        return modelMapper.map(book,Book.class);
    }
}
