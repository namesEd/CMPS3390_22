package a11.ekyles.caloriecounter;

public class CalorieItem {
    private final String num;

    public CalorieItem(String num)  {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    @Override
    public String toString(){ return  this.getNum(); }

}