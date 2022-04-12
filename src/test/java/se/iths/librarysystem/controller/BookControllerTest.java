package se.iths.librarysystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.librarysystem.dto.Book;
import se.iths.librarysystem.entity.BookEntity;
import se.iths.librarysystem.mocks.WithMockAdmin;
import se.iths.librarysystem.security.SecurityConfig;
import se.iths.librarysystem.service.BookService;
import se.iths.librarysystem.validatorservice.BookValidator;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookController bookController;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private BookValidator bookValidator;

    @BeforeEach
    void setUp() {

        List<Book> books = List.of(
                new Book().setId(1L).setIsbn("90009"),
                new Book().setId(2L).setIsbn("90009")
        );

        when(bookService.getAllBooks()).thenReturn(books);
        when(bookService.getBooksByIsbn(anyString())).thenReturn(books);
    }

    @WithMockUser
    @Test
    void getAllBooksShouldReturnAllBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].isbn").value("90009"));
        verify(bookService).getAllBooks();
    }

    @WithMockUser
    @Test
    void getBooksByIsbnShouldReturnBooks() throws Exception {
        mockMvc.perform(get("/api/books").param("isbn", "90009"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].isbn").value("90009"));
        verify(bookService).getBooksByIsbn(anyString());
    }


    @WithMockAdmin
    @Test
    void createBookShouldReturnCreatedBook() throws Exception {
        Book book = new Book().setIsbn("90009");

        when(bookService.createBook(any(Book.class))).thenReturn(book.setId(28L));

        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(28))
                .andExpect(redirectedUrlPattern("**/api/books/" + 28));

    }

    @WithMockUser
    @Test
    void findBookByIdShouldReturnBook() throws Exception {
        Book book = new Book().setIsbn("90009").setId(36L);

        when(bookService.findBookById(anyLong())).thenReturn(book);

        mockMvc.perform(get("/api/books/{id}", 75))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(36));

    }

}
