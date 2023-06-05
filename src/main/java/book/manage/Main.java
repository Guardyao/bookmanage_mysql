package book.manage;

import book.manage.Sql.SqlUtil;
import book.manage.entity.Book;
import book.manage.entity.Borrow;
import book.manage.entity.Student;
import book.manage.mapper.Mapper;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;

@Log
public class Main {
    static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {

        LogManager manager = LogManager.getLogManager();
        manager.readConfiguration(Resources.getResourceAsStream("logging.properties"));

        while(true) {
            System.out.println("------------------------");
            System.out.println("----欢迎来到图书管理系统----");
            System.out.println("------1.录入学生信息------");
            System.out.println("------2.录入书籍信息------");
            System.out.println("------3.录入借阅信息------");
            System.out.println("------4.查询学生信息------");
            System.out.println("------5.查询书籍信息------");
            System.out.println("------6.查询借阅信息------");
            System.out.println("-------输入其他退出-------");
            System.out.print("输入你需要的操作:");
            int input;
            try {
                input = scanner.nextInt();
                //若输入不是int,通过异常直接结束
            } catch(Exception e){
                System.out.println("欢迎下次使用！");
                return;
            }
            //吸掉换行符
            scanner.nextLine();
            switch (input) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    addBorrow();
                    break;
                case 4:
                    showStudent();
                    break;
                case 5:
                    showBook();
                    break;
                case 6:
                    showBorrow();
                    break;
                default:
                    System.out.println("欢迎下次使用！");
                    return;
            }
        }

    }

    private static void addStudent(){

        Student student = new Student();


        System.out.print("请输入学生姓名：");
        student.setName(scanner.nextLine());


        System.out.print("请输入学生性别(男/女)：");
        String sex = scanner.nextLine();
        while (!sex.equals("男") && !sex.equals("女")){
            System.out.print("请输入正确的性别！");
            System.out.println();
            System.out.print("输入学生性别(男/女)：");
            sex = scanner.nextLine();
        }
        student.setSex(sex);


        System.out.print("请输入年级：");
        int grade;
        while(true) {
            try {
                grade = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("请输入正确的年级(数字)！");
                System.out.print("重新输入你的年级(数字)：");
                scanner.nextLine();
            }
        }
        student.setGrade(grade);
        System.out.println(student);

        SqlUtil.doSqlWork(mapper ->{
            int s = mapper.addStudent(student);
            if (s > 0) {
                System.out.println("录入成功");
                log.log(Level.INFO,"添加一条学生信息" + student,true);
            }
            else System.out.println("录入失败，请重试！");
        });


    }

    public static void addBook(){

        Book book = new Book();
        System.out.print("请输入图书名字:");
        book.setTitle(scanner.nextLine());

        System.out.print("请输入图书作者:");
        book.setAuthor(scanner.nextLine());

        System.out.print("请输入图书价格:");
        double price;
        while(true) {
            try {
                price = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("请输入正确的价格(数字)！");
                System.out.print("重新输入价格(数字)：");
                scanner.nextLine();
            }
        }
        book.setPrice(price);

        System.out.println(book);

        SqlUtil.doSqlWork(mapper ->{
            int s = mapper.addBook(book);
            if (s > 0) {
                System.out.println("录入成功");
                log.log(Level.INFO,"添加一条图书信息"+book,true);
            }
            else System.out.println("录入失败，请重试！");
        });
    }

    private static void addBorrow(){
        System.out.print("请输入借阅人id：");
        int sid;
        while(true) {
            try {
                sid = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("输入错误！请重新输入！");
                scanner.next();
            }
        }
        int finalSid = sid;
        System.out.print("请输入书籍id:");
        int bid;
        while(true) {
            try {
                bid = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("输入错误！请重新输入！");
                scanner.next();
            }
        }
        int finalBid = bid;
        SqlUtil.doSqlWork(mapper -> {
            int s = mapper.addBorrow(finalSid,finalBid);
            if (s > 0){
                System.out.println("录入成功");
                log.log(Level.INFO,"添加一条借阅信息学生："+finalSid+"借了书籍："+finalBid,true);
            }else System.out.println("录入失败，请重试");
        });
    }
    public static void showStudent(){
        System.out.println("学生信息如下：");
        SqlUtil.doSqlWork(mapper -> {
            mapper.showStudent().forEach(System.out::println);
        });
    }
    public static void showBook(){
        System.out.println("图书信息如下：");
        SqlUtil.doSqlWork(mapper -> {
            mapper.showBook().forEach(System.out::println);
        });
    }
    public static void showBorrow(){
        System.out.println("借阅信息如下：");
        SqlUtil.doSqlWork(mapper -> {
            mapper.showBorrow().forEach(System.out::println);
        });
    }
}
