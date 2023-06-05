package book.manage.entity;


import lombok.Data;

@Data
public class Student {
    int sid;
    private String name;
    private String sex;
    private int grade;

//    public Student( String name, String sex, int grade) {
//        this.name = name;
//        this.sex = sex;
//        this.grade = grade;
//    }
public String toString() {
    return "Student(name=" + this.getName() + ", sex=" + this.getSex() + ", grade=" + this.getGrade() + ")";
}
}
