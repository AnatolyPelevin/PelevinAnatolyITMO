import java.util.Arrays;
import java.util.List;

public class TestLibrary {
    public static void testLibrary(){
        System.out.println("****Start test library.****");
        System.out.println("Init books' List.");
        List<Book> listOfBooks = Arrays.asList(
                new Book("Dima", "Title Dima 1", 100),
                new Book("Anna", "Title Anna 1", 60),
                new Book("Oly", "Title Oly 1", 500),
                new Book("Victor", "Title Victor 1", 1000),
                new Book("Stas", "Title Stas 1", 1000)
        );

        System.out.println("Init library");
       Library library = new Library();
       System.out.println("Add books to the library");
       listOfBooks.stream().forEach(book -> {
           printBook(book);
           library.putBook(book, 20);
       });

        System.out.println("");
        System.out.println("Check get books from library!");
        for (int i = 0; i < listOfBooks.size(); i++) {
            Book book =  listOfBooks.get(i);
            int bookLeftInLibrary = library.getBook(book, (i+1)*3);
            printBook(book);
            System.out.println("Book took: " + i + ". Books left: " + bookLeftInLibrary);
        }

        System.out.println("");
        System.out.println("Check put books to the library!");
        int numberPutBook;
        for (int i = 0; i < listOfBooks.size(); i++) {
            numberPutBook = (i+1)*2;
            Book book =  listOfBooks.get(i);
            int booksWasInLibrary = library.getBookCurrentQuantity(book);
            int booksNowInLibrary = library.putBook(book, numberPutBook);
            printBook(book);
            StringBuilder sb = new StringBuilder()
                    .append("Book was in library: ")
                    .append(booksWasInLibrary)
                    .append(". Books added to the library: ")
                    .append(numberPutBook)
                    .append(". Now books in the library: ")
                    .append(booksNowInLibrary);
            System.out.println(sb.toString());
        }

        Book book  = new Book("Pushkin", "Best one", 200);
        System.out.println("");
        System.out.println("Check put negative books quantity to the library!");
        printBook(book);
        System.out.println("Put -10 books!");
        library.putBook(book, -10);

        System.out.println("");
        System.out.println("Check get more books that we have in the library!");
        System.out.println("Put 2 books!");
        library.putBook(book, 2);
        System.out.println("Get 3 books!");
        library.getBook(book, 3);
    }

    private static void printBook(Book book) {
        System.out.println(book.toString());
    }

}
