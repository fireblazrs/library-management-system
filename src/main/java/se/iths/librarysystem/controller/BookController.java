package se.iths.librarysystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.iths.librarysystem.dto.Book;
import se.iths.librarysystem.dto.BookFormat;
import se.iths.librarysystem.entity.BookEntity;
import se.iths.librarysystem.entity.BookFormatEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.exceptions.ValueNotFoundException;
import se.iths.librarysystem.service.BookService;
import se.iths.librarysystem.validatorservice.BookValidator;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;
    private final BookValidator bookValidator;
    private final ModelMapper modelMapper;
    private Object BookFormatEntity;


    public BookController(BookService bookService, BookValidator bookValidator, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
        this.modelMapper = modelMapper;
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

//    @GetMapping("{id}/bookformats")
//    public ResponseEntity<BookFormat> getBookFormatConnectedToBook(@PathVariable Long id) {
//        bookValidator.validId(id);
//        BookEntity bookEntity = bookService.findById(id).orElseThrow(() -> new IdNotFoundException("book", id));
//        BookFormatEntity bookFormatEntity = (BookFormatEntity) Optional.ofNullable(bookEntity.getBookFormatEntities())
//                .orElseThrow(() -> new ValueNotFoundException("bookformat", "/books/" + id + "/bookformat"));
//        BookFormat bookFormat = modelMapper.map(BookFormatEntity, BookFormat.class);
//        return new ResponseEntity<>(bookFormat, HttpStatus.OK);
//    }



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
