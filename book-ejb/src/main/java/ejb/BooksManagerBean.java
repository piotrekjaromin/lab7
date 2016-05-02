package ejb;

import model.Book;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

@Stateful
@Remote(BooksManager.class)
public class BooksManagerBean implements BooksManager {

    @EJB
    BookBox box;

    public String borrowBook(int id) {
        Book book = box.getBooksList().get(id);

        if(book.isBorrowed()) return "Error: book is already borrowed";

        if(book.isReserved()) return "Error: book is already reserved";

        book.setBorrowed(true);

        return "Success: borrowed book";
    }

    public String reserveBook(int id) {
        Book book = box.getBooksList().get(id);

        if(book.isBorrowed()) return "Error: book is already borrowed";

        if(book.isReserved()) return "Error: book is already reserved";

        book.setReserved(true);

        return "Success: reserved book";
    }

    public String cancelReservation(int id) {
        Book book = box.getBooksList().get(id);

        if(book.isReserved()) {
            book.setReserved(false);
            return "Success: reservation cancelled";
        }

        return "Error: book is not reserved";
    }

    public String returnBook(int id) {
        Book book = box.getBooksList().get(id);

        if(book.isBorrowed()) {
            book.setBorrowed(false);
            return "Success: book returned";
        }

        return "Error: book is not borrowed";
    }


}
