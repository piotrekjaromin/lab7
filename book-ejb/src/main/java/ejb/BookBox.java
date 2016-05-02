package ejb;

import model.Book;
import model.Books;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;


@Singleton
@Startup
public class BookBox {
    private Books books = new Books();

    @PostConstruct
    public void setupBooks() throws JAXBException {
        File file = new File("/home/piotrek/Projects/lab7/book-ejb/books.xsd");

        JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        books = (Books) jaxbUnmarshaller.unmarshal(file);
    }

    @Lock(READ)
    public List<Book> getBooksList() {
        return books.getBooks();
    }

    @Lock(WRITE)
    public void reserve(int bookId ) {
        Book book = books.getBooks().get(bookId);
        book.setReserved(true);
    }

    @Lock(WRITE)
    public void borrow(int bookId ) {
        Book book = books.getBooks().get(bookId);
        book.setBorrowed(true);
    }

    @Lock(WRITE)
    public void returnReserved(int bookId ) {
        Book book = books.getBooks().get(bookId);
        book.setReserved(false);
    }

    @Lock(WRITE)
    public void returnBorrowed(int bookId ) {
        Book book = books.getBooks().get(bookId);
        book.setBorrowed(false);
    }
}
