package se.iths.librarysystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.librarysystem.entity.AuthorEntity;
import se.iths.librarysystem.service.AuthorService;

import java.util.Optional;


@RestController
@RequestMapping("api/authors")
public class AuthorController {

    AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping()
    public ResponseEntity<AuthorEntity> createAuthor(@RequestBody AuthorEntity authorEntity){

        AuthorEntity createdAuthor = authorService.createAuthor(authorEntity);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){

        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<AuthorEntity>> findAuthorById(@PathVariable Long id) {
        Optional<AuthorEntity> foundAuthor = authorService.findAuthorById(id);
        return new ResponseEntity<>(foundAuthor, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<Iterable<AuthorEntity>> findAllAuthors() {
        Iterable<AuthorEntity> allAuthors = authorService.findAllAuthors();
        return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }
}
