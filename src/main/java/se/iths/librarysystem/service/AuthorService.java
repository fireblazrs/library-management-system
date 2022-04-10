package se.iths.librarysystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import se.iths.librarysystem.dto.Author;
import se.iths.librarysystem.entity.AuthorEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public List<Author> getAllAuthors() {
        Iterable<AuthorEntity> authorEntities = authorRepository.findAll();
        List<AuthorEntity> authorEntityList = new ArrayList<>();
        authorEntities.forEach(authorEntityList::add);
        return authorEntityList.stream().map(author -> modelMapper.map(author, Author.class)).toList();
    }

    public Author createAuthor(Author author) {
        AuthorEntity authorEntity= modelMapper.map(author, AuthorEntity.class);
        AuthorEntity savedAuthor = authorRepository.save(authorEntity);
        return modelMapper.map(savedAuthor, Author.class);
    }


    public void updateAuthor(AuthorEntity author) {
        authorRepository.save(author);
    }

    public Author findAuthorById(Long id){
        AuthorEntity foundAuthor = authorRepository.findById(id).orElseThrow(() -> new IdNotFoundException("author", id));
        return modelMapper.map(foundAuthor, Author.class);
    }

    public void deleteAuthor(Long id){
        AuthorEntity foundAuthor = authorRepository.findById(id).orElseThrow(() -> new IdNotFoundException("author", id));
        authorRepository.deleteById(foundAuthor.getId());
    }

}

