package ir.maktabsharif101.hw8.utility;

public class ZereshkThrower extends Exception{
    private String message="OOPS!";

    public ZereshkThrower(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
