package ir.maktabsharif101.hw8.repository;

import ir.maktabsharif101.hw8.base.repository.BaseRepository;
import ir.maktabsharif101.hw8.entity.Cart;

import java.sql.SQLException;
import java.util.List;

public interface CartRepository extends BaseRepository<Integer, Cart> {

    List<Cart> listAllInCart(Integer user_id) throws SQLException;
    boolean hasItemsInCart(Integer user_id) throws SQLException;
    int countOfItemInCart(Integer item_id) throws SQLException;
    String nameOfItemInCart(Integer item_id) throws SQLException;

}
