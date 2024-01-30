package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;


public class UserDao {
    private SimpleConnectionMaker simpleConnectionMaker;
    final static String url = "jdbc:mysql://localhost:3306/spring";
    final static String userName = "root";
    final static String password = "12341234";
    public UserDao(){
        simpleConnectionMaker = new SimpleConnectionMaker();
    }
    public void add(User user) throws  SQLException {
        Connection c = simpleConnectionMaker.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into user(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws  SQLException {
        Connection c = simpleConnectionMaker.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from user where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
}
