package se.iths.librarysystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.librarysystem.entity.AuthorEntity;
import se.iths.librarysystem.entity.BookFormatEntity;
import se.iths.librarysystem.service.BookFormatService;

import java.util.Optional;


@RestController
@RequestMapping("bookformats")
public class BookFormatController {

    BookFormatService bookFormatService;

    public BookFormatController(BookFormatService bookFormatService){
        this.bookFormatService = bookFormatService;
    }

    @PostMapping()
    public ResponseEntity<BookFormatEntity> createBookFormat(@RequestBody BookFormatEntity bookFormatEntity){

        BookFormatEntity createdBookFormat = bookFormatService.createBookFormat(bookFormatEntity);
        return new ResponseEntity<>(createdBookFormat, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBookFormat(@PathVariable Long id){

        bookFormatService.deleteBookFormat(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<BookFormatEntity>> findBookFormatById(@PathVariable Long id) {
        Optional<BookFormatEntity> foundBookFormat = bookFormatService.findBookFormatById(id);
        return new ResponseEntity<>(foundBookFormat, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<Iterable<BookFormatEntity>> findAllBookFormats() {
        Iterable<BookFormatEntity> allBookFormats = bookFormatService.findAllBookFormats();
        return new ResponseEntity<>(allBookFormats, HttpStatus.OK);
    }
}
