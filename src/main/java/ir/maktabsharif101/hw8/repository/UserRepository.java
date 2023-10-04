package ir.maktabsharif101.hw8.repository;

import ir.maktabsharif101.hw8.base.repository.BaseRepository;
import ir.maktabsharif101.hw8.entity.User;

import java.sql.SQLException;

public interface UserRepository extends BaseRepository<Integer, User> {
    User login(String username, String password_) throws SQLException;
    boolean doesExist(String username) throws SQLException;

}
