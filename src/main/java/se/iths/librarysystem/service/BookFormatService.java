package se.iths.librarysystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import se.iths.librarysystem.dto.BookFormat;
import se.iths.librarysystem.entity.BookFormatEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.repository.BookFormatRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookFormatService {

    private final BookFormatRepository bookFormatRepository;
    private final ModelMapper modelMapper;

    public BookFormatService(BookFormatRepository bookFormatRepository, ModelMapper modelMapper) {
        this.bookFormatRepository = bookFormatRepository;
        this.modelMapper = modelMapper;
    }

    public List<BookFormat> getAllBookFormats() {
        Iterable<BookFormatEntity> bookFormatEntities = bookFormatRepository.findAll();
        List<BookFormatEntity> bookFormatEntityList = new ArrayList<>();
        bookFormatEntities.forEach(bookFormatEntityList::add);
        return bookFormatEntityList.stream().map(bookFormat -> modelMapper.map(bookFormat, BookFormat.class)).toList();
    }

    public BookFormat createBookFormat(BookFormat bookFormat) {
        BookFormatEntity bookFormatEntity= modelMapper.map(bookFormat, BookFormatEntity.class);
        BookFormatEntity savedBookFormat = bookFormatRepository.save(bookFormatEntity);
        return modelMapper.map(savedBookFormat, BookFormat.class);
    }


    public void updateBookFormat(BookFormatEntity bookFormat) {
        bookFormatRepository.save(bookFormat);
    }

    public BookFormat findBookFormatById(Long id){
        BookFormatEntity foundBookFormat = bookFormatRepository.findById(id).orElseThrow(() -> new IdNotFoundException("book format", id));
        return modelMapper.map(foundBookFormat, BookFormat.class);
    }

    public void deleteBookFormat(Long id){
        BookFormatEntity foundBookFormat = bookFormatRepository.findById(id).orElseThrow(() -> new IdNotFoundException("book format", id));
        bookFormatRepository.deleteById(foundBookFormat.getId());
    }

}