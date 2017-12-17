package core;

import javafx.collections.ObservableList;
import product.Product;

public class ShopController {
    private ShopView view;
    private ShopModel model;

    public ShopController(ShopModel model, ShopView view) {
        this.view = view;
        this.model = model;
    }

    public  ObservableList<Product> getObservableListOfProducts()
    {
        return  model.getObservableListOfProducts();
    }

}
