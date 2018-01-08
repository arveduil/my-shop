package core;

import javafx.collections.ObservableList;
import product.*;

import java.util.ArrayList;

public class ShopController {
    private ShopView view;
    private ShopModel model;

    public ShopController(ShopModel model, ShopView view) {
        this.view = view;
        this.model = model;
    }

    public ObservableList<Product> getObservableListOfProducts() {
        return model.getObservableListOfProducts();
    }

    public void buy(Triple selectedTriple, String amount, Product selectedProduct) {
        view.result.setText("kupuje");
        int intAmount = Integer.parseInt(amount);
        if (selectedTriple instanceof TripleCharSize) {
            TripleCharSize tripleCharSize = new TripleCharSize(((TripleCharSize) selectedTriple).getSize(), intAmount, selectedTriple.getGender());
            checkTransaction(tripleCharSize, selectedProduct);
        }
        if (selectedTriple instanceof TripleNumericSize) {
            TripleNumericSize tripleNumericSize = new TripleNumericSize(((TripleNumericSize) selectedTriple).getSize(), intAmount, selectedTriple.getGender());
            checkTransaction(tripleNumericSize, selectedProduct);


        }
    }

    private void checkTransaction(Triple tripleTest, Product selectedProduct) {
        ArrayList<Triple> quantitiy = (ArrayList) selectedProduct.getQuantity();
        for (Triple t : quantitiy) {
            if (t.getGender() == tripleTest.getGender())
                if (t.getSize() == tripleTest.getSize())
                    if (t.getAmmount() >= tripleTest.getAmmount()) {
                        t.setAmmount(t.getAmmount() - tripleTest.getAmmount());
                        view.printResult("Congretulations!\nYou have bought\n"+ tripleTest.getAmmount() +" units of \n" + selectedProduct.getName() );
                        model.checkProducts();
                        view.refreshListOfProductsAndDescription(this);

                        return;
                    }
        }
        view.printResult("Out of stock");
        view.refreshListOfProductsAndDescription(this);
    }
   public void createShirt(Shirt shirt){
        model.addProduct(shirt);
       view.printResult("Congretulations!\nYou created new Shirt" );
       model.checkProducts();

       view.refreshListOfProductsAndDescription(this);

   }

    public void createShoes(Shoes createdShoes) {
        model.addProduct(createdShoes);
        view.printResult("Congretulations!\nYou created new product" );
        model.checkProducts();

        view.refreshListOfProductsAndDescription(this);
    }

    public void createTrousers(Trousers createdTrousers) {
        model.addProduct(createdTrousers);
        view.printResult("Congretulations!\nYou created new product" );
        model.checkProducts();

        view.refreshListOfProductsAndDescription(this);
    }
}
