package product;


import java.math.BigDecimal;
import java.util.ArrayList;

public class Trousers extends Product {
    private Boolean areWaterproof;

    public Trousers(int productID, int weight, int barCode, BigDecimal price, String name, Category category, ArrayList<Triple>  quantity, Boolean areWaterproof) {
        super(productID, weight, barCode, price, name, category, quantity);
        this.areWaterproof = areWaterproof;
    }
}
