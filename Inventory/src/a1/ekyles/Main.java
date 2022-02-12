package a1.ekyles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Random ran = new Random();
    private static final FoodItems[] foodItems = FoodItems.values();
    private static final Tools[] tools = Tools.values();
    private static final ToolUses[] toolUses = ToolUses.values();
    private static final Potions[] potions = Potions.values();
    private static final PotionUses[] potionUses = PotionUses.values();

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        //Srt of the app
        System.out.print("How many items do you want: ");
        int itemCnt = Integer.parseInt(scan.nextLine());

        for(int i = 0; i < itemCnt; i++) {
            int type = ran.nextInt(3);
            switch(type) {
                case 0 -> items.add(genFood());
                case 1 -> items.add(genTool());
                case 2 -> items.add(genPotion());
            }
        }

        for(Item i : items){
            System.out.println(i);
        }

    }
    public static Food genFood() {
        int foodIndex = ran.nextInt(foodItems.length);
        String foodName = foodItems[foodIndex].toString();
        float foodPrice = ran.nextFloat(10);
        int foodQty = ran.nextInt(35);
        int foodUses = ran.nextInt(7);
        float healthGain = ran.nextFloat(25);
        return new Food(foodName, foodPrice, foodQty, foodUses, healthGain);
    }

    public static Tool genTool(){
        int toolIndex = ran.nextInt(tools.length);
        String toolName = tools[toolIndex].toString();
        float toolPrice = ran.nextFloat(200);
        int toolQty = ran.nextInt(15);
        String use = toolUses[toolIndex].toString();
        return new Tool(toolName, toolPrice, toolQty, use);
    }

    public static Potion genPotion(){
        int potionIndex = ran.nextInt(potions.length);
        String potionName = potions[potionIndex].toString();
        float potionPrice = ran.nextFloat(125);
        int potionQty = ran.nextInt(5);
        String uses = potionUses[potionIndex].toString();
        return new Potion(potionName, potionPrice, potionQty, uses);
    }
}
