package product;

import java.math.BigDecimal;
import java.util.ArrayList;

public  class Shoes extends Product {

    private ShoeType shoeType;
    private Material mainMaterial;

    @Override
    public ArrayList<TripleNumericSize> getQuantity() {
        return quantity;
    }

    private  ArrayList<TripleNumericSize> quantity;

    public Shoes(int productId, int weight, int barCode, BigDecimal price, String name, Category category, ArrayList<TripleNumericSize>  quantity, ShoeType shoeType, Material mainMaterial ) {
        super(productId, weight, barCode, price, name,  category, quantity);
        this.shoeType = shoeType;
        this.mainMaterial = mainMaterial;
        this.quantity = quantity;
    }

    public void setQuantity(ArrayList<TripleNumericSize> quantity) {
        this.quantity = quantity;
    }

    public ShoeType getShoeType() {
        return shoeType;
    }

    public Material getMainMaterial() {
        return mainMaterial;
    }

}
