package springbook.user.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;


public class UserDao {

    private DataSource dataSource;
    private JdbcContext jdbcContext;
    final static String url = "jdbc:mysql://localhost:3306/spring";
    final static String userName = "root";
    final static String password = "12341234";

    public UserDao(ConnectionMaker connectionMaker){
       this.dataSource = new SingleConnectionDataSource(
                url, userName, password, true
        );
    }

    public void deleteAll() throws SQLException {
       this.jdbcContext.executeSql("delete from user");
    }



    public void add(final User user) throws  SQLException {
        this.jdbcContext.workWithStatementStrategy(new StatementStrategy(){
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement("insert into user(id, name, password) values(?, ?, ?)");

                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
                return ps;
            }
        });
    }

    public void setJdbcContext(JdbcContext jdbcContext){
        this.jdbcContext = jdbcContext;
    }



    // 수정자 메소드이면서 jdbcContext에 대한 생성, DI 작업을 동시 수행
    public void setDataSource(DataSource dataSource){
        this.jdbcContext = new JdbcContext();
        // DI
        this.jdbcContext.setDataSource(dataSource);

        this.dataSource = dataSource;
    }

    public int getCount() throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            c = dataSource.getConnection();

            ps = c.prepareStatement(
                    "select count(*) from user");
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch (SQLException e){
            throw e;
        }finally {
            if(rs != null){
                try{
                    rs.close();
                }catch (SQLException e){

                }
            }

            if(ps != null){
                try{
                    ps.close();
                }catch (SQLException e){

                }
            }

            if(c != null){
                try{
                    c.close();
                }catch (SQLException e){

                }
            }
        }
    }

    public User get(String id) throws  SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from user where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        User user = null;
        if(rs.next()){
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        rs.close();
        ps.close();
        c.close();

        if(user == null) throw new EmptyResultDataAccessException(1);
        return user;
    }


}
