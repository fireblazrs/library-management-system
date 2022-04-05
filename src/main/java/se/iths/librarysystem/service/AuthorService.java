package se.iths.librarysystem.service;

import org.springframework.stereotype.Service;
import se.iths.librarysystem.entity.AuthorEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.repository.AuthorRepository;

import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Iterable<AuthorEntity> findAllAuthors() {
        return authorRepository.findAll();
    }

    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    public Optional<AuthorEntity> findAuthorById(Long id){return authorRepository.findById(id);}

    public void deleteAuthor(Long id){
        AuthorEntity foundAuthor = authorRepository.findById(id).orElseThrow(() -> new IdNotFoundException("author", id));
        authorRepository.deleteById(foundAuthor.getId());
    }

}
