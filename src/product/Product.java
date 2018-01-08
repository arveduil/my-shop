package product;

import java.math.BigDecimal;
import java.util.ArrayList;

public abstract class Product {



    private int productID;
    private int weight;
    private int barCode;
    private BigDecimal price;
    private String name;
    private Category category;
    private  Object quantity;

    public Product(int productID)
    {
        this.productID = productID;
    }

    public Product(int productID, int weight, int barCode, BigDecimal price, String name, Category category, Object quantity) {
        this.productID = productID;
        setWeight(weight);
         setPrice(price);
        this.barCode = barCode;
        this.price = price;
        this.name = name;
        this.category = category;
        setQuantity(quantity);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if(weight < 0)
            throw new IllegalArgumentException("Weight must be greater than zero");
        this.weight = weight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if(price.signum() != 1)
            throw new IllegalArgumentException("Price must be greater than zero");
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public int getBarCode() {
        return barCode;
    }

    public String getName() {
        return name;
    }


    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return productID == product.productID;
    }

    @Override
    public int hashCode() {
        return productID;
    }

    public Object getQuantity() {
        return quantity;
    }

    public void setQuantity(Object quantity) {
        this.quantity = quantity;
    }

}
