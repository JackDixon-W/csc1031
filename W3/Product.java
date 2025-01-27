//package W3;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String productName;
    private long price;
    private boolean inStock;
    private List<String> tags = new ArrayList<>(); 

    public Product(String productName, long price, boolean inStock, List<String> tags){
        if (productName != null)
        {
            this.productName = productName;
        } else {
            this.productName = "Unknown";
        }

        if (price >= 0) {
            this.price = price;
        } else {
            this.price = 0;
        }

        this.inStock = inStock;

        if (tags != null) {
            this.tags = new ArrayList<String>(tags);;
        } else {
            this.tags = new ArrayList<>();
        }
    }

    // No Parameters (productName, price, inStock, tags)
    public Product(){
        this("Unknown", 0, false, new ArrayList<String>());
    }

    // No tags
    public Product(String productName, long price, boolean inStock)
    {
        this(productName, price, inStock, new ArrayList<String>());
    }

    // No price, inStock, tags
    public Product(String productName){
        this(productName, 0, false, new ArrayList<String>());
    }

    // No inStock, tags
    public Product(String productName, long price){
        this(productName, price, false, new ArrayList<String>());
    }

    // No inStock
    public Product(String productName, long price, List<String> tags){
        this(productName, price, false, tags);
    }

    // No productName, price, inStock
    public Product(List<String> tags)
    {
        this("Unknown", 0, false, tags);
    }

    // Deep Copy
    public Product(Product oldProduct)
    {
        this(oldProduct.productName, oldProduct.price, oldProduct.inStock, oldProduct.tags);
    }

    // Return current tags list
    public List<String> getTags(){
        return this.tags;
    }

    // Set tags list
    public void setTags(List<String> tags){
        this.tags = tags;
    }

    // Add tag to tags list
    public void addTag(String tag){
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                ", tags=" + tags +
                '}';
    }
}
