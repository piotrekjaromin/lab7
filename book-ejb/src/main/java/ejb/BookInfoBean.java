package ejb;

import model.Book;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote(BookInfo.class)
public class BookInfoBean implements BookInfo {

    @EJB
    BookBox box;
    @Override
    public String printBookInfo() {
        List<Book> books= box.getBooksList();
        StringBuffer sb = new StringBuffer();
        for (Book book: books) {
            sb.append(book.toString());
            sb.append("\n");
        }
        return sb.toString();
    }


}
