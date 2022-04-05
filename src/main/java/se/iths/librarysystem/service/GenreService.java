package se.iths.librarysystem.service;

import org.springframework.stereotype.Service;
import se.iths.librarysystem.entity.GenreEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.repository.GenreRepository;

import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Iterable<GenreEntity> findAllGenres() {
        return genreRepository.findAll();
    }

    public GenreEntity createGenre(GenreEntity genreEntity) {
        return genreRepository.save(genreEntity);
    }

    public Optional<GenreEntity> findGenreById(Long id){return genreRepository.findById(id);}

    public void deleteGenre(Long id){
        GenreEntity foundGenre = genreRepository.findById(id).orElseThrow(() -> new IdNotFoundException("genre", id));
        genreRepository.deleteById(foundGenre.getId());
    }

}
