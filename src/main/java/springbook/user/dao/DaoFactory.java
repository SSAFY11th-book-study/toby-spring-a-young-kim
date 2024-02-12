package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    static UserDao userDao = new UserDao();
    //@Bean
    public UserDao userDao() {
        return userDao;

    }
    @Bean
    public UserDao userDao2(ConnectionMaker connectionMaker) {
        return new UserDao(connectionMaker);
    }
    /*@Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }*/

    @Bean
    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker();
    }
}
