package product;


import java.math.BigDecimal;
import java.util.ArrayList;

public class Trousers extends Product {


    private Boolean areWaterproof;


    private Boolean removableLegs;
    @Override
    public ArrayList<TripleCharSize> getQuantity() {
        return quantity;
    }


    private ArrayList<TripleCharSize> quantity;

    public Trousers(int productID, int weight, int barCode, BigDecimal price, String name, Category category, ArrayList<TripleCharSize>  quantity, Boolean areWaterproof, Boolean removableLegs) {
        super(productID, weight, barCode, price, name, category, quantity);
        this.areWaterproof = areWaterproof;
        this.quantity = quantity;
        this.removableLegs = removableLegs;
    }


    public void setQuantity(ArrayList<TripleCharSize> quantity) {
        this.quantity = quantity;
    }

    public Boolean getAreWaterproof() {
        return areWaterproof;
    }

    public Boolean getRemovableLegs() {
        return removableLegs;
    }

}
