import java.util.Hashtable;

/**Должно быть как минимум два класса: Book и Library. \
        Library имеет два метода: void put(Book book, int quantity) и int get(Book book, int quantity).
        */

public final class Library {
    private Hashtable<Book, Integer> booksHashTable;

    public  Library (){
       this.booksHashTable =  new Hashtable<>();
    }
    public Library (Hashtable<Book, Integer> booksHashTable){
        this.booksHashTable =  booksHashTable;
    }

    public int getBookCurrentQuantity(Book book){
        return booksHashTable.get(book);
    }

    public int putBook(Book book, int quantity) {
        if (quantity<0) {
            System.out.println("Wrong quantity. Must be positive!");
            return -1;
        }

        int countBooksToPut = quantity;
        if (booksHashTable.containsKey(book)) {
            countBooksToPut += booksHashTable.get(book);
        }
        booksHashTable.put(book, countBooksToPut);
        return booksHashTable.get(book);
    }

    public int getBook(Book book, int quantity){
        if (!booksHashTable.containsKey(book)) {
            System.out.println("Library does not have such book!");
            return -1;
        }

        if (quantity<0) {
            System.out.println("Can't take negative book amount!");
            return -1;
        }

        int count  = booksHashTable.get(book);

        if (count < quantity) {
            System.out.println("Library does not have such amount for book you have ordered!");
            return -1;
        }

        int newCount = count - quantity;
        booksHashTable.put(book, newCount);

        return newCount;
    }
}
