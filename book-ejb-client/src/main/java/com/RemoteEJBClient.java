package com;

import ejb.BookInfo;
import ejb.BooksManager;
import utils.IOUtils;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;



public class RemoteEJBClient {
    private final static Logger logger = Logger.getLogger(RemoteEJBClient.class .getName());
    private final static Hashtable jndiProperties = new Hashtable();

    public static void main(String[] args) throws Exception {
        Logger.getLogger("org.jboss").setLevel(Level.SEVERE);
        Logger.getLogger("org.xnio").setLevel(Level.SEVERE);

        testRemoteEJB();

    }

    private static void testRemoteEJB() throws NamingException {

        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        final BookInfo info = lookupTheatreInfoEJB();

        final BooksManager book = lookupTheatreBookerEJB();

        dumpWelcomeMessage();

        String command = "";
        Future<String> futureResult = null;

        while (true){

            command = IOUtils.readLine("> ");
            if (command.equals("reserve")) {

                int id = 0;

                try {
                    id = IOUtils.readInt("Enter id");
                } catch (NumberFormatException e1) {
                    logger.info("Wrong id format!");
                    continue;
                }

                    String retVal = book.reserveBook(id);
                    System.out.println(retVal);

            }

            else if (command.equals("borrow")) {
                int id = 0;

                try {
                    id = IOUtils.readInt("Enter id");
                } catch (NumberFormatException e1) {
                    logger.info("Wrong id format!");
                    continue;
                }

                String retVal = book.borrowBook(id);
                System.out.println(retVal);
            }

            else if (command.equals("return")) {
                int id = 0;

                try {
                    id = IOUtils.readInt("Enter id");
                } catch (NumberFormatException e1) {
                    logger.info("Wrong id format!");
                    continue;
                }

                String retVal = book.returnBook(id);
                System.out.println(retVal);
            }

            else if (command.equals("cancelReservation")) {
                int id = 0;

                try {
                    id = IOUtils.readInt("Enter id");
                } catch (NumberFormatException e1) {
                    logger.info("Wrong id format!");
                    continue;
                }

                String retVal = book.cancelReservation(id);
                System.out.println(retVal);
            }
            else if (command.equals("list")) {
                logger.info(info.printBookInfo().toString());
                continue;
            }
            else if (command.equals("quit")) {
                logger.info("Bye");
                break;
            }
            else {
                logger.info("Unknown command "+command);

            }
        }

    }



    private static BookInfo lookupTheatreInfoEJB() throws NamingException {

        final Context context = new InitialContext(jndiProperties);
        return (BookInfo) context.lookup("ejb:/book-ejb//BookInfoBean!ejb.BookInfo");

    }
    private static BooksManager lookupTheatreBookerEJB() throws NamingException {

        final Context context = new InitialContext(jndiProperties);
        return (BooksManager) context.lookup("ejb:/book-ejb//BooksManagerBean!ejb.BooksManager?stateful");

    }

    public static void dumpWelcomeMessage() {
        System.out.println("Book system");
        System.out.println("=====================================");
        System.out.println("Commands: reserve, borrow, return, cancelReservation, list, quit");

    }
}