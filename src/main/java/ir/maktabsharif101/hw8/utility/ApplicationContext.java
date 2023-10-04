package ir.maktabsharif101.hw8.utility;


import ir.maktabsharif101.hw8.connection.JdbcConnection;
import ir.maktabsharif101.hw8.repository.CartRepositoryImpl;
import ir.maktabsharif101.hw8.repository.UserRepository;
import ir.maktabsharif101.hw8.repository.UserRepositoryImpl;
import ir.maktabsharif101.hw8.service.CartServiceImpl;
import ir.maktabsharif101.hw8.service.UserService;
import ir.maktabsharif101.hw8.service.UserServiceImpl;
import ir.maktabsharif101.hw8.utility.menu.HomePageMenu;
import ir.maktabsharif101.hw8.utility.menu.MainMenu;


import java.awt.*;
import java.sql.Connection;
import java.util.Scanner;

public class ApplicationContext {
    private static final Connection CONNECTION;
    private static final UserRepositoryImpl USER_REPOSITORY_IMPL;
    private static final CartRepositoryImpl CART_REPOSITORY_IMPL;
    private static final UserServiceImpl USER_SERVICE_IMPL;
    private static final CartServiceImpl CART_SERVICE_IMPL;
    private static final HomePageMenu HOME_PAGE_MENU;
    private static final MainMenu MAIN_MENU;

    private static final Scanner SCANNER;


    static {
        CONNECTION = JdbcConnection.getConnection();
        USER_REPOSITORY_IMPL = new UserRepositoryImpl(CONNECTION);
        CART_REPOSITORY_IMPL = new CartRepositoryImpl(CONNECTION);
        USER_SERVICE_IMPL = new UserServiceImpl(USER_REPOSITORY_IMPL);
        CART_SERVICE_IMPL = new CartServiceImpl(CART_REPOSITORY_IMPL);
        HOME_PAGE_MENU = new HomePageMenu(USER_SERVICE_IMPL, CART_SERVICE_IMPL);
        MAIN_MENU = new MainMenu(USER_SERVICE_IMPL, CART_SERVICE_IMPL);
        SCANNER = new Scanner(System.in);
    }

    public static Connection getConnection() {
        return CONNECTION;
    }

    public static Scanner getScanner() {
        if (SCANNER != null)
            return SCANNER;
        else
            return new Scanner(System.in);
    }

    public static UserServiceImpl getUserServiceImpl() {
        return USER_SERVICE_IMPL;
    }

    public static HomePageMenu getHomePageMenu() {
        return HOME_PAGE_MENU;
    }

    public static MainMenu getMainMenu() {
        return MAIN_MENU;
    }
}
