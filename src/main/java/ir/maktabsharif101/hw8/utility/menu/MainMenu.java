package ir.maktabsharif101.hw8.utility.menu;

import ir.maktabsharif101.hw8.entity.Cart;
import ir.maktabsharif101.hw8.entity.User;
import ir.maktabsharif101.hw8.entity.enums.Electronics;
import ir.maktabsharif101.hw8.entity.enums.Shoes;
import ir.maktabsharif101.hw8.service.CartServiceImpl;
import ir.maktabsharif101.hw8.service.UserServiceImpl;
import ir.maktabsharif101.hw8.utility.ApplicationContext;
import ir.maktabsharif101.hw8.utility.Validation;
import ir.maktabsharif101.hw8.utility.ZereshkThrower;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private final UserServiceImpl userServiceImpl;
    private final CartServiceImpl cartServiceImpl;
    public static User user = HomePageMenu.user;
    Scanner scanner = ApplicationContext.getScanner();

    public MainMenu(UserServiceImpl userServiceImpl, CartServiceImpl cartServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.cartServiceImpl = cartServiceImpl;
    }

    public void Menu() throws SQLException, ZereshkThrower {
        boolean isMenuActive = true;
        while (isMenuActive) {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|                HomeWork8-Maktab101               |");
            System.out.println("|                    OnlineShop                    |");
            System.out.println("|           Developed by: Sina Afzalsoltani        |");
            System.out.println("+--------------------------------------------------+");
            System.out.println();
            System.out.println();
            System.out.println("Choose an option from the menu:");
            System.out.println("1- View list of all goods in Electronics category");
            System.out.println("2- View list of all goods in Shoes category");
            System.out.println("3- Buy an item");
            System.out.println("4- View your cart");
            System.out.println("5- Remove item from your cart");
            System.out.println("6- Exit");

            String input = scanner.next();
            scanner.nextLine();

            switch (input) {
                case "1" -> listOfElectronics();
                case "2" -> listOfShoes();
                case "3" -> buyItem();
                case "4" -> viewCart();
                case "5" -> removeFromCart();
                case "6" -> isMenuActive = false;
                default -> {
                    System.out.println("Invalid input!");
                    scanner.nextLine();
                    System.out.println("**********  Press any key to continue   **********");
                    scanner.nextLine();
                }
            }
        }
    }

    public void listOfElectronics() {
        System.out.println("    *****    List of available items to buy in ELECTRONICS!    *****     ");
        System.out.println("     Name     |     Unit price     |     Available count     ");
        System.out.println("=============================================================");
        for (Electronics E : Electronics.values()) {
            System.out.println("     " + E.getProductName() + "          " + E.getPRICE() + "$           " + E.getCountsAvailable());
            System.out.println("-------------------------------------------------------------");
        }
    }

    public void listOfShoes() {
        System.out.println("    *****    List of available items to buy in Shoes!    *****     ");
        System.out.println("     Name     |     Unit price     |     Available count     ");
        System.out.println("=============================================================");
        for (Shoes S : Shoes.values()) {
            System.out.println("     " + S.getProductName() + "          " + S.getPRICE() + "$           " + S.getCountsAvailable());
            System.out.println("-------------------------------------------------------------");
        }
    }


    public void buyItem() {
        System.out.println("Enter the name of item to buy: ");
        String itemName = scanner.nextLine();
        try {
            Electronics electronics = Electronics.valueOf(itemName.toUpperCase());
            int neededCount = getInteger("How many do you need?");
            if (Validation.isNumberOfItemsAvailable(itemName, neededCount)) {
                cartServiceImpl.save(new Cart(electronics.getProductName(), electronics.getPRICE(), neededCount, user.getId()));
                electronics.setCountsAvailable(electronics.getCountsAvailable() - neededCount);
            } else {
                System.out.println("You can buy a maximum number of " + electronics.getCountsAvailable() + " from this item!");
            }
        } catch (IllegalArgumentException | SQLException e) {
            try {
                Shoes shoes = Shoes.valueOf(itemName.toUpperCase());
                int neededCount = getInteger("How many do you need?");
                if (Validation.isNumberOfItemsAvailable(itemName, neededCount)) {
                    cartServiceImpl.save(new Cart(shoes.getProductName(), shoes.getPRICE(), neededCount, user.getId()));
                    shoes.setCountsAvailable(shoes.getCountsAvailable() - neededCount);
                } else {
                    System.out.println("you can buy a maximum number of " + shoes.getCountsAvailable() + " from this item!");
                }
            } catch (IllegalArgumentException | SQLException e2) {
                System.out.println("Item is not available!");
            }
        }
    }

    public void viewCart() throws SQLException {
        try {
            if (cartServiceImpl.hasItemsInCart(user.getId())) {
                System.out.println("     ID     |     item name     |     unit price     |     count     ");
                System.out.println("=====================================================================");
                List<Cart> itemsInCart = cartServiceImpl.listAllInCart(user.getId());
                double totalPrice = 0;
                for (Cart item : itemsInCart) {
                    System.out.println("     " + item.getId() + "          " + item.getItemName() + "          " + item.getItemUnitPrice() + "$          " + item.getItemCount());
                    System.out.println("---------------------------------------------------------------------");
                    totalPrice += item.getItemUnitPrice() * item.getItemCount();
                }
                System.out.println("////////////////////////////  =====> Total cost: " + totalPrice + "$");
            } else {
                throw new ZereshkThrower("Your cart is empty! ");
            }
        } catch (ZereshkThrower z) {
            System.out.println(z.getMessage());
        }
    }

    public void removeFromCart() throws SQLException {
        int itemID = getInteger("Enter the ID of item you wish to be removed from your cart:");
        int tempCount;
        try {
            if (cartServiceImpl.doesExist(itemID)) {
                tempCount = cartServiceImpl.countOfItemInCart(itemID);

                try {
                    Electronics electronics = Electronics.valueOf(cartServiceImpl.nameOfItemInCart(itemID));
                    electronics.setCountsAvailable(electronics.getCountsAvailable() + tempCount);
                } catch (IllegalArgumentException e) {
                    Shoes shoes = Shoes.valueOf(cartServiceImpl.nameOfItemInCart(itemID));
                    shoes.setCountsAvailable(shoes.getCountsAvailable() + tempCount);
                }
                cartServiceImpl.delete(itemID);
                System.out.println("Item is deleted from your cart!");
            } else {
                throw new ZereshkThrower("Item does not exist in your cart!");
            }
        } catch (ZereshkThrower z) {
            System.out.println(z.getMessage());
        }
    }

    public Integer getInteger(String message) {
        int input;
        while (true) {
            try {
                System.out.println(message);
                input = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // clear the input buffer
            }
        }
        return input;
    }


}
