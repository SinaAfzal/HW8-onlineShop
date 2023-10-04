package ir.maktabsharif101.hw8.repository;

import ir.maktabsharif101.hw8.base.repository.BaseRepositoryImpl;
import ir.maktabsharif101.hw8.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl extends BaseRepositoryImpl<Integer, User> implements UserRepository {

private final Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    public String getColumnNames() {
        return "(fullName,userName,password_)";
    }

    @Override
    public String getQuestionMarks() {
        return "(?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "fullName=?, userName=?, password_=?";
    }

    @Override
    public void fillParamStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getFullName());
        preparedStatement.setString(2, user.getUserName());
        preparedStatement.setString(3, user.getPassword());

    }

    @Override
    public void getGeneratedKeys(ResultSet resultSet, User user) throws SQLException {

        while (resultSet.next()) {
            user.setId(resultSet.getInt(1));
        }
    }



    @Override
    public void delete(Integer id) throws SQLException {
        String query="DELETE FROM cart WHERE user_id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
        String query2="DELETE FROM users WHERE id=?";
        PreparedStatement preparedStatement1=connection.prepareStatement(query2);
        preparedStatement1.setInt(1,id);
        preparedStatement1.executeUpdate();
    }

    @Override
    public User login(String username, String password_) throws SQLException {
        String query = "SELECT * FROM users WHERE (username=? AND password_=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password_);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setFullName(resultSet.getString(2));
            user.setUserName(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));

            return user;
        }
        return null;
    }

    @Override
    public boolean doesExist(String username) throws SQLException {
        String query="SELECT id FROM users WHERE username=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,username);
        ResultSet resultSet=preparedStatement.executeQuery();
        return resultSet.next();
    }
}
