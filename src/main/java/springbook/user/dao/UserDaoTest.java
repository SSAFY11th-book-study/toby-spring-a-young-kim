package springbook.user.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import springbook.user.domain.User;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTest {
    private UserDao dao;
    private User user1, user2, user3;
    @Before
    public void setUp(){
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.dao = context.getBean("userDao", UserDao.class);

        this.user1 = new User("gyumee", "박성철", "springno1");
        this.user2 = new User("gyumee2", "박성철2", "springno2");
        this.user3 = new User("gyumee3", "박성철3", "springno3");

    }
    @Test
    public void addAdnGet() throws SQLException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userget = dao.get(user1.getId());
        assertThat(userget.getName(), is(user1.getName()));
        assertThat(userget.getPassword(), is(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(), is(user2.getName()));
        assertThat(userget2.getPassword(), is(user2.getPassword()));
    }
    @Test
    public void count() throws SQLException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }
    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));
        dao.get("unknown_id");
    }


    public static void main(String[] args) {
        JUnitCore.main("springbook.user.dao.UserDaoTest");
    }
}
