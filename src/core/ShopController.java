package core;

import javafx.collections.ObservableList;
import product.*;

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

    public   void buy(Tuple selectedTuple, String amount , Product selectedProduct){
        view.result.setText("kupuje");
        int intAmount = Integer.parseInt(amount);
            if(selectedTuple instanceof TripleCharSize){
                TripleCharSize tripleCharSize = new TripleCharSize(((TripleCharSize) selectedTuple).getSize(),intAmount, selectedTuple.getGender());
                checkTransationForCharSize(tripleCharSize,selectedProduct);
            }
        if(selectedTuple instanceof TripleNumericSize){
            TripleNumericSize tripleNumericSize = new TripleNumericSize(((TripleNumericSize) selectedTuple).getSize(),intAmount, selectedTuple.getGender());

        }
    }

    private   void checkTransationForCharSize(TripleCharSize tripleCharSizeWantedToBuy, Product selectedProduct){
        if(selectedProduct instanceof Trousers){
            for (TripleCharSize t :((Trousers) selectedProduct).getQuantity()) {
                if(t.getGender() == tripleCharSizeWantedToBuy.getGender() )
                    if(t.getSize() == tripleCharSizeWantedToBuy.getSize())
                        if(t.getAmmount() >= tripleCharSizeWantedToBuy.getAmmount()){
                            t.setAmmount(t.getAmmount() - tripleCharSizeWantedToBuy.getAmmount());
                            model.modifyProducts(selectedProduct);
                            view.result.setText("Correct!");
                        }
                        else
                            view.result.setText("Out of stock");


            }
        }
        view.result.setText("Out of stock");
    }

    public void refreshWithModel(){
        view.refreshListOfProducts(this);
    }
}
