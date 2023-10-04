package ir.maktabsharif101.hw8.repository;

import ir.maktabsharif101.hw8.base.repository.BaseRepositoryImpl;
import ir.maktabsharif101.hw8.entity.Cart;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartRepositoryImpl extends BaseRepositoryImpl<Integer, Cart> implements CartRepository {


    private final Connection connection;

    public CartRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public String getTableName() {
        return "cart";
    }

    @Override
    public String getColumnNames() {
        return "(itemname,itemunitprice,itemcount,user_id)";
    }

    @Override
    public String getQuestionMarks() {
        return "(?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "itemname=?,itemunitprice=?,itemcount=?,user_id=?";
    }

    @Override
    public void fillParamStatement(PreparedStatement preparedStatement, Cart cart) throws SQLException {
        preparedStatement.setString(1, cart.getItemName());
        preparedStatement.setDouble(2, cart.getItemUnitPrice());
        preparedStatement.setInt(3, cart.getItemCount());
        preparedStatement.setInt(4, cart.getUserID());
    }

    @Override
    public void getGeneratedKeys(ResultSet resultSet, Cart cart) throws SQLException {
        while (resultSet.next()) {
            cart.setId(resultSet.getInt(1));
        }
    }

    @Override
    public List<Cart> listAllInCart(Integer user_id) throws SQLException {
        String query = "SELECT * FROM cart WHERE user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Cart> listAllInCart = new ArrayList<>();
        while (resultSet.next()) {
            listAllInCart.add(
                    new Cart(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5))
            );
        }
        return listAllInCart;
    }

    @Override
    public boolean hasItemsInCart(Integer user_id) throws SQLException {
        String query = "SELECT COUNT(*) FROM cart WHERE user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int countOfItemsInCart = resultSet.getInt(1);
        return countOfItemsInCart != 0;
    }

    @Override
    public int countOfItemInCart(Integer item_id) throws SQLException {
        String query = "SELECT itemcount FROM cart WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, item_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    @Override
    public String nameOfItemInCart(Integer item_id) throws SQLException {
        String query = "SELECT itemname FROM cart WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, item_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else return null;
    }
}
