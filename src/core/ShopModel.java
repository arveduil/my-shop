package core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import product.*;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShopModel {
    ObservableList<Product> allProductsList;

    public ShopModel(){
        allProductsList = FXCollections.observableArrayList(
                new Shoes(1, 50230, 1234  ,  BigDecimal.valueOf(99.99) , "Super Sandals xD",   Category.Shoes,
                        modifyQuantity(null, 41, 10, Gender.Male), ShoeType.Sandals , "Leather"),
                new Trousers(2, 500, 2, BigDecimal.valueOf(129.99), "Walker trousers ",  Category.Trousers,
                        modifyQuantity(
                                modifyQuantity(
                                        modifyQuantity(null, Size.M, 10,  Gender.Male)
                                        , Size.M , 100, Gender.Female)
                                ,Size.S , 12, Gender.Female)
                        , true)
                );
    }
   public ObservableList<Product> getObservableListOfProducts()
   {
       return allProductsList;
   }

   public ArrayList<TripleNumericSize> modifyQuantity(ArrayList<TripleNumericSize> quantity, Integer size, int ammount, Gender gender ){
        if(quantity == null) quantity = new ArrayList<TripleNumericSize> ();
        for (TripleNumericSize tripleNumericSize : quantity  ) {
           if(tripleNumericSize.getSize() == size && tripleNumericSize.getGender() == gender) {
                tripleNumericSize.ammount += ammount;
                return  quantity;
               }
           }

           quantity.add( new TripleNumericSize(size,ammount, gender));
        return  quantity;
    }

    public ArrayList<TripleCharSize> modifyQuantity(ArrayList<TripleCharSize> quantity, Size size, int ammount, Gender gender ){
        if(quantity == null) quantity = new ArrayList<TripleCharSize> ();
        for (TripleCharSize tripleCharSize : quantity  ) {
            if(tripleCharSize.getSize() ==  size && tripleCharSize.getGender() == gender) {
                tripleCharSize.ammount += ammount;
                return  quantity;
            }
        }

        quantity.add( new TripleCharSize(size,ammount, gender));
        return  quantity;
    }

    public void modifyProducts(Product selectedProduct) {
        for (Product p : allProductsList) {
            if(p.getProductID() == selectedProduct.getProductID()){
                allProductsList.remove(p);
                allProductsList.add(selectedProduct);
            }
        }
    }
}
