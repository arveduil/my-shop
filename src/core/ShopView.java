package core;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.StringConverter;
import product.*;

import java.util.ArrayList;
import java.util.Collections;

public class ShopView {
    Window window;
    Scene scene;
    Controller controller;
    ComboBox<Tuple> selectedPossibleTripleComboBox;
    ListView<Product>listView;
    public Label result;

    public Scene PrepareScene(ShopController controller) {
        selectedPossibleTripleComboBox = new ComboBox<Tuple>();

        setupComboBox();

        result= new Label("result");
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        VBox centerVBox = new VBox(10);
        centerVBox.setPadding(new Insets(10, 10, 10, 10));
        VBox leftVBox = new VBox(10);
        leftVBox.setPadding(new Insets(10, 10, 10, 10));
        Button buyButton = new Button("Buy");
        BorderPane borderPane = new BorderPane();
        TextField chooseAmmountToBuy = new TextField();
        Button showSelected = new Button("Poka sowe");
        Button buySelected = new Button("Buy");

        refreshListOfProducts(controller);

        prepareListView();

        Label description = new Label("lol");

        vBox.getChildren().add(listView);

        prepareChoseAmmountToBuy(chooseAmmountToBuy);


        buyButton.setOnAction(e-> controller.buy( selectedPossibleTripleComboBox.getSelectionModel().getSelectedItem(), chooseAmmountToBuy.getCharacters().toString(),listView.getSelectionModel().getSelectedItems().get(0)));

        leftVBox.getChildren().addAll(description, new Label("\nChoose size: "), selectedPossibleTripleComboBox, new Label("\nChoose amount:"), chooseAmmountToBuy);
        centerVBox.getChildren().addAll(showSelected,buySelected,result);
        showSelected.setOnAction(e -> showSelected(listView, description));

        borderPane.setRight(vBox);
        borderPane.setCenter(leftVBox);
        borderPane.setLeft(centerVBox);

        scene = new Scene(borderPane, 600, 600);

        return scene;
    }

    private void setupComboBox() {



        selectedPossibleTripleComboBox.setConverter(new StringConverter<Tuple>() {
            @Override
            public String toString(Tuple tuple) {
                return tuple == null ? "" : getStringFromTuple(tuple);
            }

            @Override
            public Tuple fromString(String string) {
                return null;
            }
        });
    }

    private void prepareChoseAmmountToBuy(TextField chooseAmmountToBuy) {
        chooseAmmountToBuy.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    chooseAmmountToBuy.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    private void prepareListView() {
        listView.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> p) {

                final ListCell<Product> cell = new ListCell<Product>() {

                    @Override
                    protected void updateItem(Product t, boolean bln) {
                        super.updateItem(t, bln);

                        if (t != null) {
                            setText(t.getName());
                        } else {
                            setText("");
                        }
                    }
                };
                return cell;
            }
        });
    }

    private void showSelected(ListView listview, Label label) {
        if (listview.getSelectionModel().isEmpty()) return;
        ObservableList<Product> observableList = listview.getSelectionModel().getSelectedItems();
        Product selectedProduct = observableList.get(0);
        selectedPossibleTripleComboBox.getItems().clear();
        setupComboBox();
        for (Object t: ((ArrayList) selectedProduct.getQuantity())  ) {
            if(t instanceof  Tuple)
            selectedPossibleTripleComboBox.getItems().add((Tuple) t);

        }
        label.setText(createDescriptionOfProduct(selectedProduct));

    }


    private String createDescriptionOfProduct(Product selectedProduct) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ");
        stringBuilder.append(selectedProduct.getName());
        stringBuilder.append("\n");

        stringBuilder.append("Category: ");
        stringBuilder.append(selectedProduct.getCategory());
        stringBuilder.append("\n");

        stringBuilder.append("Weight: ");
        stringBuilder.append(selectedProduct.getWeight());
        stringBuilder.append(" g");
        stringBuilder.append("\n");

        stringBuilder.append("Price: ");
        stringBuilder.append(selectedProduct.getPrice());
        stringBuilder.append(" &");
        stringBuilder.append("\n");

        stringBuilder.append("Available sizes: ");
        stringBuilder.append("\n");


        addGenderSizeQuantity(selectedProduct, stringBuilder);

        return stringBuilder.toString();
    }


    private void addGenderSizeQuantity(Product selectedProduct, StringBuilder stringBuilder) {
        if(selectedProduct.getQuantity() instanceof ArrayList<?>) {
            if (((ArrayList) selectedProduct.getQuantity()).get(0) instanceof TripleNumericSize) {
                ArrayList<TripleNumericSize> quantity = (ArrayList) selectedProduct.getQuantity();
                Collections.sort(quantity);
               // selectedPossibleTripleComboBox.getItems().clear();

                for (TripleNumericSize tripleNumericSize : quantity) {
                    stringBuilder.append(tripleNumericSize.getGender());
                    stringBuilder.append(" ");
                    stringBuilder.append(tripleNumericSize.getSize());
                    stringBuilder.append(" ");
                    stringBuilder.append(tripleNumericSize.getAmmount());
                    stringBuilder.append(" ");
                    stringBuilder.append("\n");

                 //   selectedPossibleTripleComboBox.getItems().add(tripleNumericSize);

                }
            }
            if (((ArrayList) selectedProduct.getQuantity()).get(0) instanceof TripleCharSize) {
                ArrayList<TripleCharSize> quantity = ((ArrayList) selectedProduct.getQuantity());
                Collections.sort(quantity);
            //    selectedPossibleTripleComboBox.getItems().clear();
                for (TripleCharSize tripleCharSize : quantity) {
                    stringBuilder.append(tripleCharSize.getGender());
                    stringBuilder.append(" ");
                    stringBuilder.append(tripleCharSize.getSize());
                    stringBuilder.append(" ");
                    stringBuilder.append(tripleCharSize.getAmmount());
                    stringBuilder.append(" ");
                    stringBuilder.append("\n");

                   // selectedPossibleTripleComboBox.getItems().add(tripleCharSize);

                }

            }
        }
    }

    private  String getStringFromTuple(Tuple tuple){
        if(tuple instanceof  TripleNumericSize){
            TripleNumericSize tripleNumericSize = (TripleNumericSize) tuple;
           return (tripleNumericSize.getGender().toString() + " " + tripleNumericSize.getSize().toString());
        }
        if(tuple instanceof  TripleCharSize){
            TripleCharSize tripleCharSize = (TripleCharSize) tuple;
            String result = tripleCharSize.getGender().toString() + " " + tripleCharSize.getSize().toString();
            return  (result);
        }
        return  null;
    }

    public void refreshListOfProducts(ShopController controller){
        listView = new ListView<Product>(controller.getObservableListOfProducts());
    }

    //public static convertTriplesToTuples
}
