//package W2;

//import java.util.List;
import java.util.ArrayList;

public class Fridge {
    private int balance;
    ArrayList<String> foodItems = new ArrayList<>();

    public Fridge(int initialBalance)
    {
        this.balance = initialBalance;
    }

    public void addFood(String item, int cost)
    {
        if (cost > balance || cost < 0 || item == null)
        {
            System.out.println("Error");
        } else {
            foodItems.add(item);
            this.balance = this.balance - cost;
            System.out.println("Item " + item + " has been added to the fridge.");
        }
    }

    public void getFood(String item)
    {
        if (foodItems.contains(item)) {
            foodItems.remove(item);
            System.out.println("Item " + item + " has been removed from the fridge.");   
        } else {
            System.out.println("Error");
        }
    }

    public void checkStatus()
    {
        System.out.println("Food items:");
        for (int i = 0; i < foodItems.size(); i++) {
            System.out.println(foodItems.get(i));
        }

        if (foodItems.size() == 0) {
            System.out.println("(none)");
        }

        System.out.println("Balance: €" + this.balance);
    }
}
