package se.iths.librarysystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.iths.librarysystem.dto.Book;

import se.iths.librarysystem.entity.BookEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.service.BookService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
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
                .created(URI.create(ServletUriComponentsBuilder.fromCurrentRequest().build().toString() + savedBook.getId()))
                .body(savedBook);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){

        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> findBookByID(@PathVariable Long id) {
        BookEntity foundBook = bookService.findBookById(id).orElseThrow(() -> new IdNotFoundException("books", id));

        Book book = modelMapper.map(foundBook, Book.class);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    }
