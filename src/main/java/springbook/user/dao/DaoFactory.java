package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {
    final static String url = "jdbc:mysql://localhost:3306/spring";
    final static String userName = "root";
    final static String password = "12341234";
    static UserDao userDao = new UserDao();
    //@Bean
    public UserDao userDao2() {
        return userDao;

    }
    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean
    public UserDao userDao3() {
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(connectionMaker());
        return userDao;
    }

    @Bean
    public DataSource dataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

// DB 연결 정보를 수정자 메소드를 통해 넣어준다. 이를 통해 오브젝트 레벨에서 DB 연결 방식을 변경 가능
        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker();
    }
}
