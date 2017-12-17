package core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import product.*;

import java.math.BigDecimal;

public class ShopView {
    Window window;
    Scene scene;
    Controller controller;

    public Scene  PrepareScene(ShopController controller){


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(100);

        Button buyButton = new Button("Buy");

        GridPane.setConstraints(buyButton, 0 , 0 );

        grid.getChildren().addAll(buyButton);

       ListView<Product> listView = new ListView<Product>(controller.getObservableListOfProducts());
        listView.setCellFactory(new Callback<ListView<Product>,ListCell<Product>>(){
            @Override
            public ListCell<Product> call(ListView<Product> p) {

                final ListCell<Product> cell = new ListCell<Product>(){

                    @Override
                    protected void updateItem(Product t, boolean bln) {
                        super.updateItem(t, bln);

                        if(t != null){
                            setText(t.getName());
                        }else{
                            setText("");
                        }
                    }
                };
                return cell;
            }
        });



        GridPane.setConstraints(listView, 0 , 0 );
        grid.getChildren().add(listView);
        scene = new Scene(grid, 600, 300);

      return  scene;
    }

//    private  ComboBox<Product> void prepareComboBox(ObservableList<Product> list){
//        ComboBox<Product> comboBox = new ComboBox(controller.getObservableListOfProducts());
//
//        comboBox.setCellFactory(new Callback<ListView<Product>,ListCell<Product>>(){
//            @Override
//            public ListCell<Product> call(ListView<Product> p) {
//
//                final ListCell<Product> cell = new ListCell<Product>(){
//
//                    @Override
//                    protected void updateItem(Product t, boolean bln) {
//                        super.updateItem(t, bln);
//
//                        if(t != null){
//                            setText(t.getName());
//                        }else{
//                            setText("");
//                        }
//                    }
//                };
//                return cell;
//            }
//        });
//
//    }
}
