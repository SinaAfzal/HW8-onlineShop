package ir.maktabsharif101.hw8;

import ir.maktabsharif101.hw8.entity.enums.Electronics;
import ir.maktabsharif101.hw8.utility.ApplicationContext;
import ir.maktabsharif101.hw8.utility.ZereshkThrower;

import java.sql.SQLException;

public class HW8Application {
    public static void main(String[] args) {
        try {
            ApplicationContext.getHomePageMenu().HomePage();
        }catch (ZereshkThrower | SQLException z){
            System.out.println(z.getMessage());
        }
    }
}
