package core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import product.*;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShopModel {
    ObservableList<Product> allProductsList;

     ShopModel() {
        allProductsList = FXCollections.observableArrayList(
                new Trousers(2, 500, 2, BigDecimal.valueOf(129.99), "Walker trousers", Category.Trousers, generateListOfCharSizes(), true, true),
                new Shoes(1, 50230, 1234, BigDecimal.valueOf(99.99), "Super Sandals", Category.Shoes, generateListOfNumericSizes(), ShoeType.Sandals, Material.Leather),
                new Trousers(3, 200, 3, BigDecimal.valueOf(199.99), "Denim Jeans", Category.Trousers, generateListOfCharSizes(), false, false),
                new Shirt(4, 200, 40897, BigDecimal.valueOf(49.99), "Thermo Shirt", Category.Shirts, generateListOfCharSizes(), true, Material.Polyester),
                new Shoes(5, 200, 478968, BigDecimal.valueOf(79.99), "Flip Flops", Category.Shirts, generateListOfNumericSizes(), ShoeType.FlipFlops, Material.Polyester)
        );
    }

    public ObservableList<Product> getObservableListOfProducts() {
        return allProductsList;
    }

    private void modifyQuantity(ArrayList<TripleNumericSize> quantity, Integer size, int ammount, Gender gender) {
        if (quantity == null) quantity = new ArrayList<TripleNumericSize>();
        for (TripleNumericSize tripleNumericSize : quantity) {
            if (tripleNumericSize.getSize() == size && tripleNumericSize.getGender() == gender) {
                tripleNumericSize.amount += ammount;
                return;
            }
        }
        quantity.add(new TripleNumericSize(size, ammount, gender));
    }

    private void modifyQuantity(ArrayList<TripleCharSize> quantity, Size size, int ammount, Gender gender) {
        if (quantity == null) quantity = new ArrayList<TripleCharSize>();
        for (TripleCharSize tripleCharSize : quantity) {
            if (tripleCharSize.getSize() == size && tripleCharSize.getGender() == gender) {
                tripleCharSize.amount += ammount;
                return;
            }
        }

        quantity.add(new TripleCharSize(size, ammount, gender));
    }

    void checkProducts() {
        for (int i = 0; i < allProductsList.size(); i++) {
            Product p = allProductsList.get(i);
            for (int j = 0; j < ((ArrayList) p.getQuantity()).size(); j++) {
                Triple triple = (Triple) ((ArrayList) p.getQuantity()).get(j);

                if (triple.amount == 0) {
                    ((ArrayList) p.getQuantity()).remove(j);
                    j--;
                }
            }
            if (((ArrayList) p.getQuantity()).isEmpty())
                allProductsList.remove(i);
        }
    }

    private Boolean checkProductId(int id) {
        for (Product p : allProductsList) {
            if (p.getProductID() == id) {
                return false;
            }

        }
        return true;
    }

    private ArrayList<TripleCharSize> generateListOfCharSizes() {
        ArrayList<TripleCharSize> quant = new ArrayList<>();
        modifyQuantity(quant, Size.L, 10, Gender.Male);
        modifyQuantity(quant, Size.S, 10, Gender.Male);
        modifyQuantity(quant, Size.XXL, 20, Gender.Male);

        modifyQuantity(quant, Size.L, 10, Gender.Female);
        modifyQuantity(quant, Size.S, 10, Gender.Female);
        modifyQuantity(quant, Size.XS, 20, Gender.Female);

        return quant;
    }

    private ArrayList<TripleNumericSize> generateListOfNumericSizes() {
        ArrayList<TripleNumericSize> quant = new ArrayList<>();
        modifyQuantity(quant, 39, 10, Gender.Male);
        modifyQuantity(quant, 40, 10, Gender.Male);
        modifyQuantity(quant, 41, 20, Gender.Male);

        modifyQuantity(quant, 42, 10, Gender.Male);
        modifyQuantity(quant, 43, 10, Gender.Male);
        modifyQuantity(quant, 44, 20, Gender.Male);

        return quant;
    }

    public String addProduct(Product product) {
        if (checkProductId(product.getProductID())) {
            allProductsList.add(product);
            allProductsList.sorted();
            return "Product added succesfully!";
        }
        return "Inwalid productID!";

    }
}
