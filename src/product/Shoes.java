package product;

import java.math.BigDecimal;
import java.util.ArrayList;

public  class Shoes extends Product {

    private ShoeType shoeType;

    private String mainMaterial;


    public Shoes(int productId, int weight, int barCode, BigDecimal price, String name,  Category category, ArrayList<Triple>  quantity, ShoeType shoeType, String mainMaterial ) {
        super(productId, weight, barCode, price, name,  category, quantity);
        this.shoeType = shoeType;
        this.mainMaterial = mainMaterial;
    }


}
