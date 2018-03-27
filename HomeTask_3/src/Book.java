/**
 * Каждой книге в библиотеке соответствует счетчик, показывающий количество хранящихся книг,
 при добавлении книги - счетчик увеличивается, при извлечении - уменьшается на число quantity.
 Поля класса Book: author, title, pagesNum.
 */


import java.util.Objects;

public class Book {
    private String author;
    private String title;
    private int pagesNum;

    public Book (String author,String title, int pagesNum ) {
        this.author = author;
        this.title = title;
        this.pagesNum = pagesNum;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPagesNum() {
        return pagesNum;
    }

    public void setPagesNum(int pagesNum) {
        this.pagesNum = pagesNum;
    }

    @Override
    public String toString() {
        return "Book [author=" + author + ", title=" + title + ", page = " + pagesNum + "]";

    }

    @Override
    public int hashCode(){
        return Objects.hash(title, pagesNum, author);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return pagesNum == book.pagesNum &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title);
    }
}
