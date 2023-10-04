package ir.maktabsharif101.hw8.entity;

import ir.maktabsharif101.hw8.base.entity.BaseEntity;


public class User extends BaseEntity<Integer> {
    private String fullName;
    private String userName;
    private String password;

    public User() {
    }

    public User(String fullName, String userName, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }

    public User(Integer integer, String fullName, String userName, String password) {
        super(integer);
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
