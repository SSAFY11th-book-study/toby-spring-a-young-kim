package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{
    final static String url = "jdbc:mysql://localhost:3306/spring";
    final static String userName = "root";
    final static String password = "12341234";
    @Override
    public Connection makeConnection() throws SQLException{
        return DriverManager.getConnection(url, userName, password);
    }
}
