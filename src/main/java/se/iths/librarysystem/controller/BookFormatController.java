package se.iths.librarysystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.iths.librarysystem.dto.BookFormat;
import se.iths.librarysystem.service.BookFormatService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/bookformats")
public class BookFormatController {

    private final BookFormatService bookFormatService;

    public BookFormatController(BookFormatService bookFormatService) {
        this.bookFormatService = bookFormatService;
    }

    @GetMapping
    public ResponseEntity<List<BookFormat>> getAllBookFormats() {
        List<BookFormat> bookFormats = bookFormatService.getAllBookFormats();
        return new ResponseEntity<>(bookFormats, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookFormat> createBookFormat(@Valid @RequestBody BookFormat bookFormat) {
        BookFormat savedBookFormat = bookFormatService.createBookFormat(bookFormat);
        return ResponseEntity
                .created(URI.create(ServletUriComponentsBuilder.fromCurrentRequest().build().toString() + savedBookFormat.getId()))
                .body(savedBookFormat);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBookFormat(@PathVariable Long id){

        bookFormatService.deleteBookFormat(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookFormat> findBookFormatByID(@PathVariable Long id) {

        BookFormat bookFormat = bookFormatService.findBookFormatById(id);
        return new ResponseEntity<>(bookFormat, HttpStatus.OK);
    }

}