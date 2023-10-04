package ir.maktabsharif101.hw8.service;

import ir.maktabsharif101.hw8.base.service.BaseService;
import ir.maktabsharif101.hw8.entity.User;

import java.sql.SQLException;

public interface UserService extends BaseService<Integer, User> {
    User login(String username, String password_) throws SQLException;
    boolean doesExist(String username) throws SQLException;
}
