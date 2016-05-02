package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "books")
public class Books {
    List<Book> books = new ArrayList<Book>();

    public Books(){}

    public List<Book> getBooks() {
        return books;
    }

    @XmlElement(name="book")
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
