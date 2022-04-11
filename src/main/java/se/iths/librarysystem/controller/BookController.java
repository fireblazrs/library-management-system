package se.iths.librarysystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.iths.librarysystem.dto.Book;
import se.iths.librarysystem.service.BookFormatService;
import se.iths.librarysystem.service.BookService;
import se.iths.librarysystem.validatorservice.BookValidator;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    @PatchMapping("{bookId}/bookformats/{bookFormatId}")
    public ResponseEntity<Book> addBookFormatToBook(@PathVariable Long bookId, @PathVariable Long bookFormatId) {
        bookValidator.validId(bookId);
        Book book = bookService.addBookFormatToBook(bookId, bookFormatId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
