package se.iths.librarysystem.validatorservice;

import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.exceptions.InvalidInputException;
import se.iths.librarysystem.service.BookService;

public class BookValidator extends LibraryValidator {

    BookService bookService;

    public BookValidator(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void validId(Long id) {
        if(id == null || id < 1L)
            throw new InvalidInputException(id + " is an invalid id.", "/books/");
    }

    @Override
    public void idExists(Long id) {
        validId(id);
        bookService.findById(id).orElseThrow(() -> new IdNotFoundException("book", id));
    }
}
