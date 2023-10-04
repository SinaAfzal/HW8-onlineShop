package ir.maktabsharif101.hw8.entity;

import ir.maktabsharif101.hw8.base.entity.BaseEntity;
import ir.maktabsharif101.hw8.entity.enums.Electronics;
import ir.maktabsharif101.hw8.entity.enums.Shoes;

public class Cart extends BaseEntity<Integer> {
private String itemName;
private double itemUnitPrice;
private int itemCount;
private int userID;

    public Cart() {
    }
    public Cart(Electronics item, int itemCount, int userID) {
        this.itemName = item.getProductName();
        this.itemUnitPrice = item.getPRICE();
        this.itemCount = itemCount;
        this.userID = userID;
    }

    public Cart(Shoes item, int itemCount, int userID) {
        this.itemName = item.getProductName();
        this.itemUnitPrice = item.getPRICE();
        this.itemCount = itemCount;
        this.userID = userID;
    }
    public Cart(String itemName, double itemUnitPrice, int itemCount, int userID) {
        this.itemName = itemName;
        this.itemUnitPrice = itemUnitPrice;
        this.itemCount = itemCount;
        this.userID=userID;
    }

    public Cart(Integer integer, String itemName, double itemUnitPrice, int itemCount,int userID) {
        super(integer);
        this.itemName = itemName;
        this.itemUnitPrice = itemUnitPrice;
        this.itemCount = itemCount;
        this.userID=userID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemUnitPrice() {
        return itemUnitPrice;
    }

    public void setItemUnitPrice(double itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
