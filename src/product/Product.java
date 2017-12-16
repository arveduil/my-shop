package product;

import java.math.BigDecimal;

public abstract class Product {

    private int productID;
    private int weight;
    private int barCode;
    private BigDecimal price;


    public Product(int productID)
    {
        this.productID = productID;
    }

    public Product(int productID, int weight, int barCode, BigDecimal price) {
        this.productID = productID;
        this.weight = weight;
        this.barCode = barCode;
        this.price = price;
    }

    public int getBarCode() {
        return barCode;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
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
}
