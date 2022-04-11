package se.iths.librarysystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.iths.librarysystem.dto.Author;
import se.iths.librarysystem.dto.Book;
import se.iths.librarysystem.dto.BookFormat;
import se.iths.librarysystem.dto.Genre;
import se.iths.librarysystem.service.BookService;
import se.iths.librarysystem.validatorservice.BookValidator;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;
    private final BookValidator bookValidator;


    public BookController(BookService bookService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String isbn) {
        List<Book> books = isbn == null
                ? bookService.getAllBooks()
                : bookService.getBooksByIsbn(isbn);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.createBook(book);
        return ResponseEntity
                .created(URI.create(ServletUriComponentsBuilder.fromCurrentRequest().build() + "/" + savedBook.getId()))
                .body(savedBook);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> findBookByID(@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("{id}/bookformats")
    public ResponseEntity<List<BookFormat>> getBookFormatsConnectedToBook(@PathVariable Long id) {
        bookValidator.validId(id);
        List<BookFormat> bookFormats = bookService.getBookFormats(id);
        return new ResponseEntity<>(bookFormats, HttpStatus.OK);
    }

    @GetMapping("{id}/genre")
    public ResponseEntity<Genre> getGenreConnectedToBook(@PathVariable Long id) {
        bookValidator.validId(id);
        Genre genre = bookService.getGenre(id);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @GetMapping("{id}/authors")
    public ResponseEntity<List<Author>> getAuthorsConnectedToBook(@PathVariable Long id) {
        bookValidator.validId(id);
        List<Author> authors = bookService.getAuthors(id);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @PatchMapping("{bookId}/bookformats/{bookFormatId}")
    public ResponseEntity<Book> addBookFormatToBook(@PathVariable Long bookId, @PathVariable Long bookFormatId) {
        bookValidator.validId(bookId);
        Book book = bookService.addBookFormatToBook(bookId, bookFormatId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PatchMapping("{bookId}/genre/{genreId}")
    public ResponseEntity<Book> addGenreToBook(@PathVariable Long bookId, @PathVariable Long genreId) {
        bookValidator.validId(bookId);
        Book book = bookService.addGenreToBook(bookId, genreId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PatchMapping("{bookId}/author/{authorId}")
    public ResponseEntity<Book> addAuthorToBook(@PathVariable Long bookId, @PathVariable Long authorId) {
        bookValidator.validId(bookId);
        Book book = bookService.addAuthorToBook(bookId, authorId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
