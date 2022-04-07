package se.iths.librarysystem.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.librarysystem.entity.GenreEntity;
import se.iths.librarysystem.service.GenreService;

import java.util.Optional;


@RestController
@RequestMapping("api/genres")
public class GenreController {

    GenreService genreService;

    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @PostMapping()
    public ResponseEntity<GenreEntity> createGenre(@RequestBody GenreEntity genreEntity){

        GenreEntity createdGenre = genreService.createGenre(genreEntity);
        return new ResponseEntity<>(createdGenre, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id){

        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<GenreEntity>> findGenreById(@PathVariable Long id) {
        Optional<GenreEntity> foundGenre = genreService.findGenreById(id);
        return new ResponseEntity<>(foundGenre, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<Iterable<GenreEntity>> findAllGenres() {
        Iterable<GenreEntity> allGenres = genreService.findAllGenres();
        return new ResponseEntity<>(allGenres, HttpStatus.OK);
    }
}
