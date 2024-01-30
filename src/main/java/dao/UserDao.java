package dao;

import springbook.user.domain.User;

import java.sql.*;

public class UserDao {
    String url = "jdbc:mysql://localhost:3306/spring";
    String userName = "root";
    String password = "12341234";
    public void add(User user) throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(url, userName, password);

        PreparedStatement ps = c.prepareStatement(
                "insert into user(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(url, userName, password);

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
