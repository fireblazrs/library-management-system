package se.iths.librarysystem.queue;

import se.iths.librarysystem.entity.BookEntity;
import se.iths.librarysystem.entity.LoanTaskEntity;
import se.iths.librarysystem.entity.UserEntity;
import se.iths.librarysystem.service.BookService;
import se.iths.librarysystem.service.LoanTaskService;
import se.iths.librarysystem.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReceiverHandler {

    private final BookService bookService;
    private final UserService userService;
    private final LoanTaskService loanTaskService;

    public ReceiverHandler(BookService bookService, UserService userService, LoanTaskService loanTaskService) {
        this.bookService = bookService;
        this.userService = userService;
        this.loanTaskService = loanTaskService;
    }

    public void loanBook(LoanTaskEntity loanTask) {
        Iterable<BookEntity> books = bookService.getBooksByIsbn(loanTask.getIsbn());
        List<BookEntity> bookList = new ArrayList<>();
        books.forEach(bookList::add);

        Optional<BookEntity> availableBook =
                bookList.stream().filter(bookEntity -> bookEntity.getBorrower() == null).findFirst();
        Optional<UserEntity> user = userService.findById(loanTask.getUserId());

        if (availableBook.isEmpty())
            loanTask.setMessage("Book ISBN " + loanTask.getIsbn() + " is not available");
        else if (user.isEmpty())
            loanTask.setMessage("User Id " + loanTask.getUserId() + " not found.");
        else {
            addBookToUser(availableBook.get(), user.get());
            updateEntities(loanTask, availableBook.get(), user.get());
        }
        loanTask.taskComplete();
        loanTaskService.updateTask(loanTask);
    }

    private void updateEntities(LoanTaskEntity loanTask, BookEntity book, UserEntity user) {
        loanTask.setSuccess(true);
        bookService.updateBook(book);
        userService.updatePerson(user);
    }

    private void addBookToUser(BookEntity book, UserEntity user) {
        user.addBook(book);
        book.setBorrower(user);
    }

}
