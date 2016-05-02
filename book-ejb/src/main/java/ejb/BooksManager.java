package ejb;

/**
 * Created by piotrek on 01.05.16.
 */
public interface BooksManager {
    public String borrowBook(int id);

    public String reserveBook(int id);

    public String cancelReservation(int id);

    public String returnBook(int id);
}
