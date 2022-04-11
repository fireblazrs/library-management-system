package se.iths.librarysystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import se.iths.librarysystem.dto.Book;
import se.iths.librarysystem.dto.BookFormat;
import se.iths.librarysystem.entity.BookEntity;
import se.iths.librarysystem.repository.AuthorRepository;
import se.iths.librarysystem.repository.BookFormatRepository;
import se.iths.librarysystem.repository.BookRepository;
import se.iths.librarysystem.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(BookService.class)
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookFormatRepository bookFormatRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private ModelMapper modelMapper;

    private String isbn;

    @BeforeEach
    public void setUp() {
        isbn = "90123456789";
        Book book = new Book().setId(1L).setIsbn(isbn);
        BookEntity bookEntity = new BookEntity().setId(1L).setIsbn(isbn);
        List<BookEntity> booksList = List.of(
                new BookEntity().setIsbn(isbn).setId(1L),
                new BookEntity().setIsbn(isbn).setId(2L),
                new BookEntity().setIsbn(isbn).setId(3L)
        );

        when(modelMapper.map(any(BookEntity.class), eq(Book.class))).thenReturn(book);
        when(modelMapper.map(any(Book.class), eq(BookEntity.class))).thenReturn(bookEntity);
        when(bookRepository.findByIsbn(anyString())).thenReturn(booksList);
    }

    @Test
    void getAllBooksShouldReturnListOfBooks() {
        Iterable<BookEntity> bookEntities = List.of(new BookEntity(), new BookEntity());

        when(bookRepository.findAll()).thenReturn(bookEntities);

        var result = bookService.getAllBooks();

        assertThat(result).hasSize(2).contains(new Book().setId(1L).setIsbn("90123456789"));

    }

    @Test
    void createBookShouldReturnBook() {
        Book book = new Book().setIsbn(isbn);
        when(bookRepository.save(any(BookEntity.class))).thenReturn(new BookEntity());

        Book result = bookService.createBook(book);

        assertThat(result).isEqualTo(new Book().setId(1L).setIsbn("90123456789"));
    }

    @Test
    void getBooksByIsbnShouldReturnListOfBooks() {
        var result = bookService.getBooksByIsbn("90123456789");

        assertThat(result).hasSize(3).contains(new Book().setIsbn("90123456789").setId(1L));
    }

    @Test
    void getBookEntityByIsbnShouldReturnListOfBooks() {
        var result = bookService.getBookEntityByIsbn("90123456789");

        assertThat(result).hasSize(3).contains(new BookEntity().setId(1L).setIsbn("90123456789"));
    }

    @Test
    void findByIdShouldReturnBookEntityOptional() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(new BookEntity().setId(22L).setIsbn("90009")));

        var result = bookService.findById(22L);

        assertThat(result).isNotEmpty().contains(new BookEntity().setId(22L).setIsbn("90009"));
    }

}