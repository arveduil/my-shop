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
                new Shoes(1, 50230, 1234  ,  BigDecimal.valueOf(99.99) , "Super Sandals xD",   Category.Shoes,  modifyQuantity(null, "41", 10, Gender.Male), ShoeType.Sandals , "Leather"),
                new Trousers(2, 500, 2, BigDecimal.valueOf(129.99), " Walker trousers Male",  Category.Trousers,
                        modifyQuantity(  modifyQuantity(null, "M", 10, Gender.Male)
                        , "M", 100, Gender.Female), true)
                );
    }
   public ObservableList<Product> getObservableListOfProducts()
   {
       return allProductsList;
   }

   public ArrayList<Triple> modifyQuantity(ArrayList<Triple> quantity, String size, int ammount, Gender gender ){
        if(quantity == null) quantity = new ArrayList<Triple> ();
        for (Triple triple : quantity  ) {
           if(triple.size == size && triple.gender == gender) {
                triple.ammount += ammount;
                return  quantity;
               }
           }
           quantity.add( new Triple(size,ammount, gender));
        return  quantity;
    }

}
