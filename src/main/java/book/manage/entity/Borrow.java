package book.manage.entity;


import lombok.Data;

@Data
public class Borrow {
    int id;
    private Student student;
    private Book book;

    public Borrow(){}


    @Override
    public String toString() {
        return "Borrow{" + "借阅编号id=" + id + ", 借阅人：" + student + ", 借阅书籍：" + book + '}';
    }
}
