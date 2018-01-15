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
        if(selectedTriple == null){
            view.printResult("Choose size, amount and gender");
            return;
        }
        if(amount.isEmpty()){
            view.printResult("Put amount");
            return;
        }
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
                    if (t.getAmount() >= tripleTest.getAmount()) {
                        t.setAmount(t.getAmount() - tripleTest.getAmount());
                        view.printResult("Congretulations! You have bought\n"+ tripleTest.getAmount() +" units of " + selectedProduct.getName() );
                        model.checkProducts();
                        view.refreshListOfProductsAndDescription();
                        return;
                    }
        }
        view.printResult("Out of stock");
        view.refreshListOfProductsAndDescription();
    }

    public void createProduct(Product product){
        switch (product.getCategory()) {
            case Shirts:
                createShirt((Shirt) product);
                break;
            case Shoes:
                createShoes((Shoes)product);
                break;
            case Trousers:

                createTrousers((Trousers) product);
                break;
        }

    }

   public void createShirt(Shirt shirt){
       view.printResult( model.addProduct(shirt));
       view.refreshListOfProductsAndDescription();
   }

    public void createShoes(Shoes createdShoes) {
        view.printResult(model.addProduct(createdShoes) );
        view.refreshListOfProductsAndDescription();
    }

    public void createTrousers(Trousers createdTrousers) {
        view.printResult(model.addProduct(createdTrousers));
        view.refreshListOfProductsAndDescription();
    }
}
