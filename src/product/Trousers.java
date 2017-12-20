package product;


import java.math.BigDecimal;
import java.util.ArrayList;

public class Trousers extends Product {
    private Boolean areWaterproof;

    @Override
    public ArrayList<TripleCharSize> getQuantity() {
        return quantity;
    }

    private ArrayList<TripleCharSize> quantity;

    public Trousers(int productID, int weight, int barCode, BigDecimal price, String name, Category category, ArrayList<TripleCharSize>  quantity, Boolean areWaterproof) {
        super(productID, weight, barCode, price, name, category, quantity);
        this.areWaterproof = areWaterproof;
        this.quantity = quantity;
    }
}
