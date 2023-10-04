package ir.maktabsharif101.hw8.service;

import ir.maktabsharif101.hw8.base.service.BaseServiceImpl;
import ir.maktabsharif101.hw8.entity.User;
import ir.maktabsharif101.hw8.repository.UserRepository;

import java.sql.SQLException;

public class UserServiceImpl extends BaseServiceImpl<Integer, UserRepository, User> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public User login(String username, String password_) throws SQLException {
        User loggedInUser = REPOSITORY.login(username,password_);
        if (loggedInUser != null) {
            System.out.println("Welcome " + loggedInUser.getFullName() + "!");
            return loggedInUser;
        } else {
            System.out.println("Invalid username or password!");
            return null;
        }
    }

    @Override
    public boolean doesExist(String username) throws SQLException {
        return REPOSITORY.doesExist(username);
    }
}
