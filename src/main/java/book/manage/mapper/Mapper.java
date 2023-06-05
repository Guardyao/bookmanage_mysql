package book.manage.mapper;

import book.manage.entity.Book;
import book.manage.entity.Borrow;
import book.manage.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface Mapper {

    //录入学生信息
    @Insert("insert into student(name,sex,grade) value(#{name},#{sex},#{grade}) ")
    int addStudent(Student student);

    //录入书籍信息
    @Insert("insert into book(title,author,price) value(#{title},#{author},#{price}) ")
    int addBook(Book book);

    //录入借阅信息
    @Insert("insert into borrow(sid,bid) value(#{sid},#{bid})")
    int addBorrow(@Param("sid")int sid, @Param("bid")int bid);

    //查询学生信息
    @Select("select * from student")
    List<Student> showStudent();

    //查询图书信息
    @Select("select * from book")
    List<Book> showBook();

    //查询借阅信息
    @Results({
        @Result(column="id",property="id",id = true),
        @Result(column="sid",property="student",one =
                @One(select = "showStudentBySid")),
        @Result(column = "bid",property = "book",one =
                @One(select = "showBookByBid"))
    })
    @Select("select * from borrow")
    List<Borrow> showBorrow();

    @Select("select * from student where sid = #{sid}")
    Student showStudentBySid(int sid);

    @Select("select * from book where bid = #{bid}")
    Book showBookByBid(int  bid);

}
