package product;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Shirt extends Product{


    private Boolean isThermoactive;
    private Material mainMaterial;


    private ArrayList<TripleCharSize> quantity;

    public Shirt(int productID, int weight, int barCode, BigDecimal price, String name, Category category, ArrayList<TripleCharSize>  quantity, Boolean isThermoactive,Material mainMaterial) {
        super(productID, weight, barCode, price, name, category, quantity);
        this.isThermoactive = isThermoactive;
        this.quantity = quantity;
        this.mainMaterial = mainMaterial;
    }

    @Override
    public ArrayList<TripleCharSize> getQuantity() {
        return quantity;
    }

    public Boolean getThermoactive() {
        return isThermoactive;
    }

    public void setThermoactive(Boolean thermoactive) {
        isThermoactive = thermoactive;
    }

    public Material getMainMaterial() {
        return mainMaterial;
    }

    public void setMainMaterial(Material mainMaterial) {
        this.mainMaterial = mainMaterial;
    }
}
