package springbook.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DaoFactory factory = new DaoFactory();
        System.out.println(factory.userDao());
        System.out.println(factory.userDao());

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        System.out.println(context.getBean("userDao", UserDao.class));
        System.out.println(context.getBean("userDao", UserDao.class));
        /*UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
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
