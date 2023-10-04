package ir.maktabsharif101.hw8.service;

import ir.maktabsharif101.hw8.base.service.BaseServiceImpl;
import ir.maktabsharif101.hw8.entity.Cart;
import ir.maktabsharif101.hw8.repository.CartRepository;
import ir.maktabsharif101.hw8.repository.CartRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class CartServiceImpl extends BaseServiceImpl<Integer, CartRepository, Cart> implements CartService {

    public CartServiceImpl(CartRepository repository) {
        super(repository);
    }

    @Override
    public List<Cart> listAllInCart(Integer user_id) throws SQLException {
        return REPOSITORY.listAllInCart(user_id);
    }

    @Override
    public boolean hasItemsInCart(Integer user_id) throws SQLException {
        return REPOSITORY.hasItemsInCart(user_id);
    }

    @Override
    public int countOfItemInCart(Integer item_id) throws SQLException {
        return REPOSITORY.countOfItemInCart(item_id);
    }

    @Override
    public String nameOfItemInCart(Integer item_id) throws SQLException {
        return REPOSITORY.nameOfItemInCart(item_id);
    }

}
