package ir.maktabsharif101.hw8.utility;

import ir.maktabsharif101.hw8.entity.enums.Electronics;
import ir.maktabsharif101.hw8.entity.enums.Shoes;
import ir.maktabsharif101.hw8.service.UserService;
import ir.maktabsharif101.hw8.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static final UserServiceImpl userServiceImpl = ApplicationContext.getUserServiceImpl();


    public static boolean isPasswordValid(String password) {
        Pattern pattern =
                Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");//at least one upper-case, one lower-case and one digit and at least 8 characters
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isUserNameValid(String username) throws SQLException {
        return (!userServiceImpl.doesExist(username) && username!=null && !username.equals(""));
    }

    public static boolean isItemNameValid(String itemName) {
        for (Electronics E : Electronics.values()) {
            if (E.getProductName().toLowerCase().equals(itemName.toLowerCase())) {
                return true;
            }
        }
        for (Shoes S : Shoes.values()) {
            if (S.getProductName().toLowerCase().equals(itemName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumberOfItemsAvailable(String itemName, int count) {
        for (Electronics E : Electronics.values()) {
            if (E.getProductName().toLowerCase().equals(itemName.toLowerCase()) && E.getCountsAvailable() >= count) {
                return true;
            }
        }
        for (Shoes S : Shoes.values()) {
            if (S.getProductName().toLowerCase().equals(itemName.toLowerCase()) && S.getCountsAvailable() >= count) {
                return true;
            }
        }
        return false;
    }
}
