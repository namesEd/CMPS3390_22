package a1.ekyles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random ran = new Random();
        List<Item> items = new ArrayList<>();
        FoodItems[] foodItems = FoodItems.values();
        Tools[] tools = Tools.values();
        ToolUses[] toolUses = ToolUses.values();
        Potions[] potions = Potions.values();
        PotionUses[] potionUses = PotionUses.values();

        System.out.print("How many items do you want: ");
        int itemCnt = Integer.parseInt(scan.nextLine());

        for(int i = 0; i < itemCnt; i++) {
            int type = ran.nextInt(3);
            switch(type) {
                case 0:
                    int foodIndex = ran.nextInt(foodItems.length);
                    String foodName = foodItems[foodIndex].toString();
                    float foodPrice = ran.nextFloat(10);
                    int foodQty = ran.nextInt(35);
                    int foodUses = ran.nextInt(7);
                    float healthGain = ran.nextFloat(25);
                    Food tmpFood = new Food(foodName, foodPrice, foodQty, foodUses, healthGain);
                    items.add(tmpFood);
                case 1:
                    int toolIndex = ran.nextInt(tools.length);
                    String toolName = tools[toolIndex].toString();
                    float toolPrice = ran.nextFloat(200);
                    int toolQty = ran.nextInt(15);
                    String use = toolUses[toolIndex].toString();
                    Tool tmpTool = new Tool(toolName, toolPrice, toolQty, use);
                    items.add(tmpTool);
                case 2:
                    int potionIndex = ran.nextInt(potions.length);
                    String potionName = potions[potionIndex].toString();
                    float potionPrice = ran.nextFloat(125);
                    int potionQty = ran.nextInt(5);
                    String uses = potionUses[potionIndex].toString();
                    Potion tmpPotion = new Potion(potionName, potionPrice, potionQty, uses);
                    items.add(tmpPotion);
            }
        }

        for(Item i : items){
            System.out.println(i);
        }

    }
}
