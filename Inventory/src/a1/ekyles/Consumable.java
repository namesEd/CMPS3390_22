package a1.ekyles;

public class Consumable extends Item{
    private int usesLeft;

    public Consumable() {
        super();
        this.usesLeft = 0;
    }

    public Consumable(String name, double price, int qty, int usesLeft) {
        super(name, price, qty);
        this.usesLeft = Math.max(usesLeft, 0);
    }

    public int getUsesLeft() {
        return usesLeft;
    }

    public void setUsesLeft(int usesLeft) {
        this.usesLeft = Math.max(usesLeft, 0);
    }

    @Override
    public String toString() {
        return String.format("%s %6d |", super.toString(), this.usesLeft);
    }

}
