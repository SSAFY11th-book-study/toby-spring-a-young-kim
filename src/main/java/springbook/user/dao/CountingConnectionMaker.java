package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker{
    int count = 0;
    private final ConnectionMaker realConnectionMaker;
    public CountingConnectionMaker(ConnectionMaker realConnectionMaker){
        this.realConnectionMaker = realConnectionMaker;
    }
    @Override
    public Connection makeConnection() throws SQLException {
       this.count += 1;
       return realConnectionMaker.makeConnection();
    }

    public int getCounter(){
        return this.count;
    }
}
