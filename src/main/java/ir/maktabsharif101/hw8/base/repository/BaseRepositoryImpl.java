package ir.maktabsharif101.hw8.base.repository;

import ir.maktabsharif101.hw8.base.entity.BaseEntity;
import ir.maktabsharif101.hw8.utility.ApplicationContext;
import org.w3c.dom.Entity;

import java.io.Serializable;
import java.sql.*;

public abstract class BaseRepositoryImpl<ID extends Serializable, Type extends BaseEntity<ID>> implements BaseRepository<ID, Type> {
    public final Connection connection= ApplicationContext.getConnection();



    public Type save(Type entity) throws SQLException {
        String query = "INSERT INTO " + getTableName() + " " + getColumnNames() + " VALUES " + getQuestionMarks();
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        fillParamStatement(preparedStatement,entity);
        preparedStatement.executeUpdate();
        ResultSet resultSet=preparedStatement.getGeneratedKeys();
        getGeneratedKeys(resultSet,entity);
        return entity;
    }

    @Override
    public int update(Type entity) throws SQLException {
        String query = "UPDATE " + getTableName() + " SET " + getUpdateQuery() + " WHERE id = " + entity.getId();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        fillParamStatement(preparedStatement,entity);
        return preparedStatement.executeUpdate();
    }
    public void delete(ID id) throws SQLException {
        String query="DELETE FROM " + getTableName() + " WHERE id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,(int)id);
        preparedStatement.executeUpdate();
    }

    public boolean doesExist(ID id) throws SQLException {
        String query="SELECT id FROM "+getTableName()+" WHERE id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,(int) id);
        ResultSet resultSet=preparedStatement.executeQuery();
        return resultSet.next();
    }

    public abstract String getTableName();

    public abstract String getColumnNames();

    public abstract String getQuestionMarks();

    public abstract String getUpdateQuery();

    public abstract void fillParamStatement(PreparedStatement preparedStatement,Type entity) throws SQLException;

    public abstract void  getGeneratedKeys(ResultSet resultSet,Type entity) throws SQLException;

}
