package se.iths.librarysystem.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.librarysystem.dto.Book;
import se.iths.librarysystem.entity.BookEntity;
import se.iths.librarysystem.repository.BookRepository;
import se.iths.librarysystem.security.SecurityConfig;
import se.iths.librarysystem.service.BookService;
import se.iths.librarysystem.validatorservice.BookValidator;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({BookService.class, SecurityConfig.class, BookValidator.class})
@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private ModelMapper modelMapper;


    @Test
    @DisplayName("An unauthenticated user should return 401: UNAUTHORIZED")
    void givenUserNotLoggedInGetAllBooksShouldReturn401() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser
    @Test
    @DisplayName("Role 'USER' should return status 200 and getAllBooks")
    void givenNonAdminUserGetAllBooksShouldReturnBooksAndStatus200() throws Exception {
        BookEntity book1 = new BookEntity().setId(1L).setTitle("Science Today").setIsbn("900000019");
        BookEntity book2 = new BookEntity().setId(2L).setTitle("Geography Today").setIsbn("900000020");
        Iterable<BookEntity> bookList = List.of(book1, book2);

        Book book = new Book().setId(1L).setTitle("Science Today").setIsbn("900000019");

        when(bookRepository.findAll()).thenReturn(bookList);
        when(modelMapper.map(any(BookEntity.class), eq(Book.class))).thenReturn(book);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].id", is(1)));
        verify(bookRepository).findAll();
    }

    @WithMockUser
    @Test
    @DisplayName("Get books by ISBN should return status 200 and getAllBooks")
    void givenNonAdminUserGetAllBooksByIsbnShouldReturnBooksAndStatus200() throws Exception {
        String isbn = "900000019";
        BookEntity bookEntity = new BookEntity().setId(21L).setIsbn(isbn);
        Book book = new Book().setId(21L).setIsbn(isbn);

        when(bookRepository.findByIsbn(isbn)).thenReturn(List.of(bookEntity));
        when(modelMapper.map(any(BookEntity.class), eq(Book.class))).thenReturn(book);

        mockMvc.perform(get("/api/books").param("isbn", isbn))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].id", is(21)))
                .andExpect(jsonPath("$[0].isbn", is("900000019")));
        verify(bookRepository).findByIsbn(isbn);
    }

    @WithMockUser
    @Test
    @DisplayName("Create new book should return book and status 201: CREATED")
    void creatingNewBookShouldReturnStatus201AndCreatedBook() throws Exception {
        String bookPayload = """
                {
                 	"title": "Science today",
                 	"subtitle": "science",
                 	"edition": "3",
                 	"printed": "2017-09-12",
                 	"isbn": "9010101016844"
                 }
                """;

        BookEntity bookEntity = new BookEntity().setTitle("Science today").setSubtitle("science")
                .setPrinted(new Date(1584342756)).setIsbn("9010101016844");
        BookEntity savedBook = new BookEntity().setId(21L).setTitle("Science today").setSubtitle("science")
                .setPrinted(new Date(1584342756)).setIsbn("9010101016844");
        Book book = new Book().setId(21L).setTitle("Science today").setSubtitle("science")
                .setPrinted(new Date(1584342756)).setIsbn("9010101016844");

        when(modelMapper.map(any(Book.class), eq(BookEntity.class))).thenReturn(bookEntity);
        when(bookRepository.save(any(BookEntity.class))).thenReturn(savedBook);
        when(modelMapper.map(any(BookEntity.class), eq(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                .content(bookPayload))
                .andExpect(status().isCreated());

    }

}