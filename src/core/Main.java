package core;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import product.*;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class Main extends Application {

    private Stage window;
    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        ShopModel model = new ShopModel();
        ShopView view = new ShopView();
        ShopController controller = new ShopController(model, view);

        scene = view.prepareScene(controller);
        window = primaryStage;

        window.setScene(scene);
        window.setTitle("MyShop");
        window.show();
    }

}