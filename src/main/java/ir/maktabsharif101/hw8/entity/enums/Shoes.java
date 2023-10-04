package ir.maktabsharif101.hw8.entity.enums;

public enum Shoes {
    SPORT(20.5,12,"Sport"),
    CASUAL(12.3,20,"Casual"),
    FORMAL(24,30,"Formal");
    private final double PRICE;
    private int countsAvailable;
    private final String productName;

    Shoes(double PRICE, int countsAvailable, String productName) {
        this.PRICE = PRICE;
        this.countsAvailable = countsAvailable;
        this.productName = productName;
    }

    public double getPRICE() {
        return PRICE;
    }

    public int getCountsAvailable() {
        return countsAvailable;
    }

    public void setCountsAvailable(int countsAvailable) {
        this.countsAvailable = countsAvailable;
    }

    public String getProductName() {
        return productName;
    }
}
