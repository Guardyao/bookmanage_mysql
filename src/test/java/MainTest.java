import book.manage.Sql.SqlUtil;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void method1(){
        SqlUtil.doSqlWork(mapper -> {
            mapper.showStudent().forEach(System.out::println);
        });
    }

    @Test
    public void method2(){
        SqlUtil.doSqlWork(mapper -> {
            mapper.showBook().forEach(System.out::println);
        });
    }

//    @Test
//    public void method3(){
//        SqlUtil.doSqlWork(mapper -> {
//            mapper.addBorrow(1,1);
//        });
//    }

    @Test
    public void method4(){
        SqlUtil.doSqlWork(mapper -> {
            mapper.showBorrow().forEach(System.out::println);
        });
    }
}
