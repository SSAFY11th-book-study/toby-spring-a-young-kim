package springbook.user.dao;

import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        DaoFactory factory = new DaoFactory();

        // 의존성 검색
        UserDao userDao1 = factory.userDao2();
        System.out.println(userDao1.hashCode());
        System.out.println(factory.userDao2().hashCode());

        System.out.println();
        // 의존성 주입
        UserDao userDao2 = factory.userDao();
        System.out.println(userDao2.hashCode());
        System.out.println(factory.userDao().hashCode());

        /*User user = new User();
        user.setId("whiteship4");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId() + "조회 성공");*/
    }
}
