package ir.maktabsharif101.hw8.entity.enums;

public enum Electronics{
    TV(2500,10,"TV"),
    RADIO(1500,20,"Radio"),
    REFRIGERATOR(3200,15,"Refrigerator");
    private final double PRICE;
    private int countsAvailable;
    private final String productName;

    Electronics(double PRICE, int countsAvailable, String productName) {
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
