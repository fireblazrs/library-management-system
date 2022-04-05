package se.iths.librarysystem.service;

import org.springframework.stereotype.Service;
import se.iths.librarysystem.entity.BookFormatEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.repository.BookFormatRepository;

import java.util.Optional;

@Service
public class BookFormatService {

    private final BookFormatRepository bookFormatRepository;

    public BookFormatService(BookFormatRepository bookFormatRepository) {
        this.bookFormatRepository = bookFormatRepository;
    }

    public Iterable<BookFormatEntity> findAllBookFormats() {
        return bookFormatRepository.findAll();
    }

    public BookFormatEntity createBookFormat(BookFormatEntity bookFormatEntity) {
        return bookFormatRepository.save(bookFormatEntity);
    }

    public Optional<BookFormatEntity> findBookFormatById(Long id){return bookFormatRepository.findById(id);}

    public void deleteBookFormat(Long id){
        BookFormatEntity foundBookFormat = bookFormatRepository.findById(id).orElseThrow(() -> new IdNotFoundException("author", id));
        bookFormatRepository.deleteById(foundBookFormat.getId());
    }

}