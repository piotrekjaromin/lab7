package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Book {
    private int id;
    private String author;
    private String title;
    private int year;
    private boolean isReserved=false;
    private boolean isBorrowed=false;

    public Book() {
    }


    public int getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    @XmlElement(name = "author", required = true)
    public void setAuthor(String author) {
        this.author = author;
    }


    public String getTitle() {
        return title;
    }

    @XmlElement(name = "title", required = true)
    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }
    @XmlElement(name = "year", required = true)
    public void setYear(int year) {
        this.year = year;
    }

    public boolean isReserved() {
        return isReserved;
    }
    @XmlElement(name = "isReserved", required = true)
    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }
    @XmlElement(name = "isBorrowed", required = true)
    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }


    public String toString() {
        return "id: " + id + ", author: " + author + ", title: " + title + ", year: " + year;
    }
}
