package book.manage.Sql;

import book.manage.mapper.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.function.Consumer;

public class SqlUtil {

    private static SqlSessionFactory sessionFactory;
    static {
        {
            try {
                sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public SqlSession getSession(){
//        return sessionFactory.openSession(true);
//    }
    public static void doSqlWork(Consumer<Mapper> consumer){
        try(SqlSession session = sessionFactory.openSession(true)) {
            Mapper mapper = session.getMapper(Mapper.class);
            consumer.accept(mapper);
        }
    }
}
