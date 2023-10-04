package ir.maktabsharif101.hw8.utility.menu;

import ir.maktabsharif101.hw8.entity.User;
import ir.maktabsharif101.hw8.service.CartServiceImpl;
import ir.maktabsharif101.hw8.service.UserServiceImpl;
import ir.maktabsharif101.hw8.utility.ApplicationContext;
import ir.maktabsharif101.hw8.utility.Validation;
import ir.maktabsharif101.hw8.utility.ZereshkThrower;

import java.sql.SQLException;
import java.util.Scanner;

public class HomePageMenu {
    private final UserServiceImpl userServiceImpl;
    private final CartServiceImpl cartServiceImpl;
    public static User user;
   Scanner scanner = ApplicationContext.getScanner();
    //Scanner scanner=new Scanner(System.in);
    public HomePageMenu(UserServiceImpl userServiceImpl, CartServiceImpl cartServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.cartServiceImpl = cartServiceImpl;
    }



    public void HomePage() throws SQLException, ZereshkThrower {
        boolean isHomePageActive = true;
        while (isHomePageActive) {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|                HomeWork8-Maktab101               |");
            System.out.println("|                    OnlineShop                    |");
            System.out.println("|           Developed by: Sina Afzalsoltani        |");
            System.out.println("+--------------------------------------------------+");
            System.out.println();
            System.out.println();
            System.out.println("Choose an option from the menu:");
            System.out.println("1- Sign Up");
            System.out.println("2- Sign in");
            System.out.println("3- Exit");

            String input = scanner.next();
            scanner.nextLine();

            switch (input) {
                case "1" -> register();
                case "2" -> login();
                case "3" -> isHomePageActive = false;
                default -> {
                    System.out.println("Invalid input!");
                    scanner.nextLine();
                    System.out.println("**********  Press any key to continue   **********");
                    scanner.nextLine();
                }
            }
        }
    }

    public void register() throws SQLException {
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter your username: ");
        String userName = null;
        boolean invalidUserName=true;
        while (invalidUserName||userName==null||userName.equals("")) {
            userName = scanner.nextLine();
            if (Validation.isUserNameValid(userName)) {
                invalidUserName = false;
            } else {
                System.out.println("Username already exists!");
                System.out.print("Enter your username: ");
            }
        }

        System.out.print("Enter your password: ");
        String password = null;
        boolean invalidPassword = true;
        while (invalidPassword) {
            password = scanner.nextLine();
            if (Validation.isPasswordValid(password)) {
                invalidPassword = false;
            } else {
                System.out.println("Enter a valid password!");
                System.out.print("Enter your password: ");
            }
        }
        User user = new User();
        user.setFullName(fullName);
        user.setUserName(userName);
        user.setPassword(password);
        userServiceImpl.save(user);
    }

    public void login() throws SQLException, ZereshkThrower {
        System.out.print("Username: ");
        String userName = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        user = userServiceImpl.login(userName, password);

        if (user != null) {
            MainMenu.user=user;
            ApplicationContext.getMainMenu().Menu();
        }
    }






}

