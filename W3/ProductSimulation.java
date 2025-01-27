package W3;

import java.util.ArrayList;
import java.util.List;

public class ProductSimulation {
    public static void main(String[] args) {
        // All Parameters
        List<String> tags1 = new ArrayList<>();
        tags1.add("Fruity");
        tags1.add("Scrumptious");
        Product fullProd = new Product("Apples", 200, true, tags1);
        System.out.println(fullProd);

        // Deep Copy test
        Product deepCopy = new Product(fullProd);
        System.out.println(deepCopy);

        // All Null
        Product allNullProduct = new Product(null, -20, false, null);
        System.out.println(allNullProduct);

        // No Parameters
        Product nothingProduct = new Product();
        System.out.println(nothingProduct);

        // No tags
        Product noTagsProduct = new Product("Banana", 15, true);
        System.out.println(noTagsProduct);
        
        // No price, inStock, tags
        Product onlyNameProduct = new Product("Cheese");
        System.out.println(onlyNameProduct);

        // No inStock, tags
        Product namePriceProduct = new Product("Milk", 5);
        System.out.println(namePriceProduct);

        // No inStock
        List<String> tags2 = new ArrayList<>();
        tags2.add("Slimey");
        tags2.add("Wet");
        Product noBoolProduct = new Product("Juice", 12, tags2);
        System.out.println(noBoolProduct);

        // Only tags
        List<String> tags3 = new ArrayList<>();
        tags3.add("Expensive");
        tags3.add("Hazardous");
        Product onlyTagsProduct = new Product("Uranium", 40000, tags3);
        System.out.println(onlyTagsProduct);

        // Empty String
        Product emptyStringProduct = new Product("");
        System.out.println(emptyStringProduct);

        // Null Tags
        Product nullTagsProduct = new Product("Sauce", 7, true, null);
        System.out.println(nullTagsProduct);

        // Negative Pricing
        List<String> tags4 = new ArrayList<>();
        tags4.add("Dark");
        tags4.add("Black");
        Product negPriceProduct = new Product("Dark Matter", -4000, true, tags4);
        System.out.println(negPriceProduct);
    }
}
