package a1.ekyles;

public class Potion extends Item{
    private String use;

    public Potion() {
        super();
        this.use = "";

    }

    public Potion(String name, double price, int qty, String use) {
        super(name, price, qty);
        this.use = use;
    }


    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }
    
    @Override
    public String toString() {
        return String.format("%s %7s %-20s |", super.toString(), "|", this.use);
                
    }
    
}
