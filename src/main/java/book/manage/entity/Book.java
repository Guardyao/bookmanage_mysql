package book.manage.entity;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Book {
    int bid;
    private String title;
    private String author;
    private double price;

    public String toString() {
        return "Book(title=《" + this.getTitle() + "》, author=" + this.getAuthor() + ", price=" + this.getPrice() + ")";
    }
}
