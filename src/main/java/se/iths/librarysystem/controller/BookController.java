package se.iths.librarysystem.controller;

import org.modelmapper.ModelMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String isbn) {
        Iterable<BookEntity> bookEntities = isbn == null
                ? bookService.getAllBooks()
                : bookService.getBooksByIsbn(isbn);

        List<Book> books = new ArrayList<>();
        bookEntities.forEach(book -> books.add(modelMapper.map(book, Book.class)));

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        BookEntity bookEntity = modelMapper.map(book, BookEntity.class);
        BookEntity savedEntity = bookService.createBook(bookEntity);
        Book savedBook = modelMapper.map(savedEntity, Book.class);
        return ResponseEntity
                .created(URI.create(ServletUriComponentsBuilder.fromCurrentRequest().build().toString() + savedEntity.getId()))
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
        // convert BookEntity to a Book DTO
        return new ResponseEntity<>(new Book(), HttpStatus.OK);
    }

    }
