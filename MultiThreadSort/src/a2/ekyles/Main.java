package a2.ekyles;
import a1.ekyles.*;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        Random ran = new Random();

        System.out.print("Do you want a [S]ingle Sort, [D]ual sort or a [Q]uad sort? ");
        char selection = scan.next().charAt(0);

        System.out.print("How many items do you want to sort? ");
        int count = scan.nextInt();

        Item[] items = new Item[count];

        for(int i = 0; i < count; i++) {
            int t = ran.nextInt(3);

            switch(t) {
                case 0:
                    items[i] = a1.ekyles.Main.genFood();
                    break;
                case 1:
                    items[i] = a1.ekyles.Main.genTool();
                    break;
                case 2:
                    items[i] = a1.ekyles.Main.genPotion();
                    break;
            }
        }

        switch (selection) {
            case 's':
            case 'S':
                SingleSort(items);
                break;
            case 'd':
            case 'D':
                DualSort(items);
                break;
            case 'q':
            case 'Q':
                QuadSort(items);
        }

    }

    private static void QuadSort(Item[] items) throws InterruptedException {
        int half = Math.round(items.length / 2f);
        int quarter = Math.round(items.length / 4f);
        int threeQuarter = Math.round(items.length / 1.333f);
        ThreadSort t1 = new ThreadSort(items, 0, quarter);
        ThreadSort t2 = new ThreadSort(items, quarter, half);
        ThreadSort t3 = new ThreadSort(items, half,threeQuarter);
        ThreadSort t4 = new ThreadSort(items, threeQuarter,items.length);

        long startTime = System.nanoTime();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        MergeSort m1 = new MergeSort(t1.gettItems(), t2.gettItems());
        m1.start();
        m1.join();

        t3.start();
        t4.start();

        t3.join();
        t4.join();

        MergeSort m2 = new MergeSort(t3.gettItems(), t4.gettItems());
        m2.start();
        m2.join();

        MergeSort m3 = new MergeSort(m1.getSortedItems(), m2.getSortedItems());
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        for(Item i : m3.getSortedItems()) {
            System.out.println(i);
        }
        System.out.println("Quad Sort took:" + duration);
    }


    private static void DualSort(Item[] items) throws InterruptedException {
        int mid = Math.round(items.length / 2f);
        ThreadSort t1 = new ThreadSort(items, 0, mid);
        ThreadSort t2 = new ThreadSort(items, mid, items.length);

        long startTime = System.nanoTime();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        MergeSort m1 = new MergeSort(t1.gettItems(), t2.gettItems());
        m1.start();
        m1.join();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        for(Item i : m1.getSortedItems()) {
            System.out.println(i);
        }
        System.out.println("Dual Sort took:" + duration);
    }


    private static void SingleSort(Item[] items) {
        //Sort before print
        ThreadSort single = new ThreadSort(items, 0, items.length);
        long startTime = System.nanoTime();
        single.start();
        try {
            single.join();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            Item[] sortedItems = single.gettItems();
            for(Item i : sortedItems) {
                System.out.println(i);
            }
            System.out.println("Was Sorted in: " +duration);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
