package core;

import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;
import javafx.util.StringConverter;
import product.*;

import java.io.File;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class ShopView {
    private ShopController controller;
    private ComboBox<Triple> selectedPossibleTripleComboBox;
    private ListView<Product> listOfProducts;
    private Label result;
    private Label description;
    private ComboBox<Category> createProductCategoryComboBox;
    private Button createProductButton;
    private VBox leftVBox;
    private VBox rightVBox;
    private VBox centerVBox;
    private Button buyButton;
    private BorderPane borderPane;
    private TextField chooseAmountToBuy;
    private VBox topVbox;

    public Scene prepareScene(ShopController controller) {
        selectedPossibleTripleComboBox = new ComboBox<Triple>();
        this.controller = controller;
        setupTripleComboBox();
        setupBasicViews();
        ImageView imageView = prepareImage();
        prepareListView();
        prepareCreateProductFeature();
        makeTextFieldNumeric(chooseAmountToBuy);

        buyButton.setOnAction(e -> controller.buy(selectedPossibleTripleComboBox.getSelectionModel().getSelectedItem(), chooseAmountToBuy.getCharacters().toString(), listOfProducts.getSelectionModel().getSelectedItems().get(0)));
        listOfProducts.getSelectionModel().selectedItemProperty().addListener((v, oldVal, newVal) -> showSelected(listOfProducts, description));

        centerVBox.getChildren().addAll(description);
        topVbox.getChildren().addAll(imageView);
        rightVBox.getChildren().addAll(result, listOfProducts, new Label("Choose size: "), selectedPossibleTripleComboBox, new Label("Choose amount:"), chooseAmountToBuy, buyButton);

        setBorderPane();
        Scene scene = new Scene(borderPane, 600, 680);
        return scene;
    }

    public void printResult(String resultOfOperation) {
        result.setText(resultOfOperation);
    }

    public void refreshListOfProductsAndDescription() {
        listOfProducts.refresh();
        showSelected(listOfProducts, description);
    }

    public String createDescriptionOfProduct(Product selectedProduct) {
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
        stringBuilder.append(" $");
        stringBuilder.append("\n");


        createDetailedDescriptionOfProduct(selectedProduct, stringBuilder);

        stringBuilder.append("--------------\n");

        stringBuilder.append("Available sizes: ");
        stringBuilder.append("\n");

        addGenderSizeQuantity(selectedProduct, stringBuilder);

        return stringBuilder.toString();
    }

    private void setBorderPane(){
        borderPane.setRight(rightVBox);
        borderPane.setCenter(centerVBox);
        borderPane.setLeft(leftVBox);
        borderPane.setTop(topVbox);
    }

    private void setupBasicViews() {
        result = new Label("Hello!");
        result.setTextAlignment(TextAlignment.JUSTIFY);
        rightVBox = new VBox(10);
        rightVBox.setPadding(new Insets(5, 10, 5, 10));
        leftVBox = new VBox(10);
        leftVBox.setPadding(new Insets(10, 10, 10, 10));
        leftVBox.setPrefWidth(150);
        centerVBox = new VBox(10);
        centerVBox.setPadding(new Insets(10, 10, 10, 10));
        topVbox = new VBox();
        topVbox.setPadding(new Insets(0, 10, 0, 10));
        topVbox.setAlignment(Pos.CENTER);
        buyButton = new Button("Buy");
        borderPane = new BorderPane();

        chooseAmountToBuy = new TextField();
        chooseAmountToBuy.setMaxWidth(50);
        description = new Label("");

    }

    private void prepareCreateProductButton() {
        createProductButton = new Button("Create Product");
        createProductButton.setOnAction(e -> createProduct());
    }

    private void prepareCreateProductFeature() {
        prepareCreateProductButton();
        createProductCategoryComboBox = new ComboBox<Category>();
        createProductCategoryComboBox.getItems().addAll(Category.values());
        prepareCreateProductInput();
        createProductCategoryComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            Category selectedCategory = createProductCategoryComboBox.getValue();
            if (oldValue != null) {
                leftVBox.getChildren().remove(leftVBox.getChildren().size() - 1, leftVBox.getChildren().size());
            }
            switch (selectedCategory) {
                case Shirts:
                    prepareShirtsCreationViews();
                    break;
                case Shoes:
                    prepareShoesCreationViews();
                    break;
                case Trousers:
                    prepareTrousersCreationViews();
                    break;
            }
        });
        createProductCategoryComboBox.valueProperty().setValue(Category.Shirts);
    }

    private ImageView prepareImage() {
        File file = new File("res/SportShop.png");
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.fitWidthProperty().setValue(500);
        imageView.fitHeightProperty().setValue(80);
        return imageView;
    }

    private void setupTripleComboBox() {
        selectedPossibleTripleComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Triple triple) {
                return triple == null ? "" : triple.getStringWithoutAmount();
            }

            @Override
            public Triple fromString(String string) {
                return null;
            }
        });
    }

    private void prepareListView( ) {
        listOfProducts = new ListView<Product>(controller.getObservableListOfProducts());

        listOfProducts.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
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
        listOfProducts.getSelectionModel().selectFirst();
    }

    private void makeTextFieldNumeric(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void showSelected(ListView listview, Label label) {
        if (listview.getSelectionModel().isEmpty()) return;
        ObservableList<Product> observableList = listview.getSelectionModel().getSelectedItems();
        Product selectedProduct = observableList.get(0);
        selectedPossibleTripleComboBox.getItems().clear();
        setupTripleComboBox();
        Collections.sort((ArrayList) selectedProduct.getQuantity());
        for (Object t : ((ArrayList) selectedProduct.getQuantity())) {
            if (t instanceof Triple)
                selectedPossibleTripleComboBox.getItems().add((Triple) t);
        }
        label.setText(createDescriptionOfProduct(selectedProduct));
    }

    private void addGenderSizeQuantity(Product selectedProduct, StringBuilder stringBuilder) {
        if (selectedProduct.getQuantity() instanceof ArrayList<?>) {
            if (((ArrayList) selectedProduct.getQuantity()).get(0) instanceof TripleNumericSize) {
                ArrayList<TripleNumericSize> quantity = (ArrayList) selectedProduct.getQuantity();
                Collections.sort(quantity);

                for (TripleNumericSize tripleNumericSize : quantity) {
                    stringBuilder.append(tripleNumericSize.getGender());
                    stringBuilder.append(" ");
                    stringBuilder.append(tripleNumericSize.getSize());
                    stringBuilder.append(" ");
                    stringBuilder.append(tripleNumericSize.getAmount());
                    stringBuilder.append(" ");
                    stringBuilder.append("\n");
                }
            }
            if (((ArrayList) selectedProduct.getQuantity()).get(0) instanceof TripleCharSize) {
                ArrayList<TripleCharSize> quantity = ((ArrayList) selectedProduct.getQuantity());
                Collections.sort(quantity);
                for (TripleCharSize tripleCharSize : quantity) {
                    stringBuilder.append(tripleCharSize.getGender());
                    stringBuilder.append(" ");
                    stringBuilder.append(tripleCharSize.getSize());
                    stringBuilder.append(" ");
                    stringBuilder.append(tripleCharSize.getAmount());
                    stringBuilder.append(" ");
                    stringBuilder.append("\n");
                }

            }
        }
    }

    private void createDetailedDescriptionOfProduct(Product selectedProduct, StringBuilder stringBuilder) {
        if (selectedProduct instanceof Shoes) {

            stringBuilder.append("Shoe type: ");
            stringBuilder.append(((Shoes) selectedProduct).getShoeType());
            stringBuilder.append("\n");

            stringBuilder.append("Main material: ");
            stringBuilder.append(((Shoes) selectedProduct).getMainMaterial());
            stringBuilder.append("\n");
        }
        if (selectedProduct instanceof Trousers) {
            if (((Trousers) selectedProduct).getAreWaterproof()) {
                stringBuilder.append("Waterproof");
                stringBuilder.append("\n");
            }

            if (((Trousers) selectedProduct).getRemovableLegs()) {
                stringBuilder.append("Removable legs");
                stringBuilder.append("\n");
            }
        }
        if (selectedProduct instanceof Shirt) {
            if (((Shirt) selectedProduct).getThermoactive()) {
                stringBuilder.append("Thermoactive");
                stringBuilder.append("\n");
            }

            stringBuilder.append("Main material: ");
            stringBuilder.append(((Shirt) selectedProduct).getMainMaterial());
            stringBuilder.append("\n");
        }
    }

    private void prepareCreateProductInput() {


        Label productIDLabel = new Label("ProductID:");
        TextField productIDTextField = new TextField();
        productIDTextField.setMaxWidth(50);
        makeTextFieldNumeric(productIDTextField);

        Label weightLabel = new Label("Product weight:");
        TextField weightTextField = new TextField();
        weightTextField.setMaxWidth(50);
        makeTextFieldNumeric(weightTextField);


        Label barCodeLabel = new Label("Bar code:");
        TextField barCodeTextField = new TextField();
        barCodeTextField.setMaxWidth(50);
        makeTextFieldNumeric(barCodeTextField);

        Label priceTextField = new Label("Price:");
        TextField priceEntireTextField = new TextField();
        priceEntireTextField.setMaxWidth(40);
        makeTextFieldNumeric(priceEntireTextField);

        TextField priceFractionTextField = new TextField();
        priceFractionTextField.setMaxWidth(40);
        makeTextFieldNumeric(priceFractionTextField);

        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();

        HBox priceHbox = new HBox();

        priceHbox.getChildren().addAll(priceEntireTextField, new Label(","), priceFractionTextField);

        leftVBox.getChildren().addAll(
                createProductButton,
                createProductCategoryComboBox,
                productIDLabel,
                productIDTextField,
                weightLabel,
                weightTextField,
                barCodeLabel,
                barCodeTextField,
                priceTextField,
                priceHbox,
                nameLabel,
                nameTextField
        );
    }

    private void createProduct() {
        Category selectedCategory = createProductCategoryComboBox.getValue();

        Integer productID = -1;
        Integer weight = -1;
        Integer barCode = -1;
        BigDecimal price = null;
        String name = null;
        Triple quantity = null;

        Object productIdObject = leftVBox.getChildren().get(3);
        Object weightObj = leftVBox.getChildren().get(5);
        Object barCodeObj = leftVBox.getChildren().get(7);
        Object priceHBoxObject = leftVBox.getChildren().get(9);
        Object nameObject = leftVBox.getChildren().get(11);
        String priceString = null;

        if (productIdObject instanceof TextField) {
            String text = (((TextField) productIdObject).getCharacters()).toString();
            if (text.isEmpty()) {
                showError("ProductID cannot be empty");
                return;
            }
            try {
                productID = Integer.parseInt(text);

            } catch (NumberFormatException e) {
                showError("Product ID is too big");
            }
        }

        if (weightObj instanceof TextField) {
            String text = (((TextField) weightObj).getCharacters()).toString();
            if (text.isEmpty()) {
                showError("Weight cannot be empty");
                return;
            }
            try {
                weight = Integer.parseInt(text);

            } catch (NumberFormatException e) {
                showError("Weight is too big");
            }
        }

        if (barCodeObj instanceof TextField) {
            String text = (((TextField) barCodeObj).getCharacters()).toString();
            if (text.isEmpty()) {
                showError("Bar code cannot be empty");
                return;
            }
            try {
                barCode = Integer.parseInt(text);

            } catch (NumberFormatException e) {
                showError("Bar Code is too big");
            }
        }


        if (priceHBoxObject instanceof HBox) {
            HBox hBox = (HBox) priceHBoxObject;
            Object unitsObj = hBox.getChildren().get(0);
            Object decObj = hBox.getChildren().get(2);

            String units = null;
            String dec = null;
            if (unitsObj instanceof TextField) {
                units = ((TextField) unitsObj).getCharacters().toString();
                if (units.isEmpty()) {
                    showError("Price cannot be empty");
                    return;
                }
            }
            if (decObj instanceof TextField) {
                dec = ((TextField) decObj).getCharacters().toString();
                if (dec.isEmpty()) {
                    showError("Price cannot be empty");
                    return;
                }
            }
            priceString = units + "." + dec;
        }

        price = new BigDecimal(priceString);

        if (nameObject instanceof TextField) {
            name = (((TextField) nameObject).getCharacters()).toString();
        }
        Object detailedVBoxObj = leftVBox.getChildren().get(12);
        VBox detailedVbox = null;
        if (detailedVBoxObj instanceof VBox)
            detailedVbox = (VBox) detailedVBoxObj;
        switch (selectedCategory) {
            case Shirts:
                Boolean isThermoactive = ((CheckBox) detailedVbox.getChildren().get(1)).isSelected();


                Material mainMaterial = (Material) ((ComboBox) detailedVbox.getChildren().get(3)).getSelectionModel().getSelectedItem();

                quantity = getCharQuantityFromCreation();
                if (quantity == null) return;
                ArrayList<TripleCharSize> quantityArrayList = new ArrayList<>();
                quantityArrayList.add((TripleCharSize) quantity);
                Shirt createdShirt = new Shirt(productID, weight, barCode, price, name, selectedCategory, quantityArrayList, isThermoactive, mainMaterial);
                controller.createShirt(createdShirt);
                break;
            case Shoes:
                ShoeType type = (ShoeType) ((ComboBox) detailedVbox.getChildren().get(1)).getSelectionModel().getSelectedItem();

                Material mainMaterialshoe = (Material) ((ComboBox) detailedVbox.getChildren().get(3)).getSelectionModel().getSelectedItem();

                quantity = getNumericQuantityFromCreation();
                if (quantity == null) return;

                ArrayList<TripleNumericSize> quantityShoeArrayList = new ArrayList<>();
                quantityShoeArrayList.add((TripleNumericSize) quantity);
                Shoes createdShoes = new Shoes(productID, weight, barCode, price, name, selectedCategory, quantityShoeArrayList, type, mainMaterialshoe);
                controller.createShoes(createdShoes);
                break;
            case Trousers:
                Boolean areWaterproof = ((CheckBox) detailedVbox.getChildren().get(1)).isSelected();
                Boolean areRemovableLegs = ((CheckBox) detailedVbox.getChildren().get(1)).isSelected();

                quantity = getCharQuantityFromCreation();
                if (quantity == null) return;

                ArrayList<TripleCharSize> quantityTrousersArrayList = new ArrayList<>();
                quantityTrousersArrayList.add((TripleCharSize) quantity);
                Trousers createdTrousers = new Trousers(productID, weight, barCode, price, name, selectedCategory, quantityTrousersArrayList, areWaterproof, areRemovableLegs);
                controller.createTrousers(createdTrousers);
                break;
        }
    }

    private TripleCharSize getCharQuantityFromCreation() {
        Object vBoxObj = leftVBox.getChildren().get(leftVBox.getChildren().size() - 1);
        Size size = null;
        Gender gender = null;
        Integer ammount = -1;
        if (vBoxObj instanceof VBox) {
            VBox vBox = (VBox) vBoxObj;
            Object sizeComboBoxObj = vBox.getChildren().get(5);
            if (sizeComboBoxObj instanceof ComboBox) {
                size = (Size) ((ComboBox) sizeComboBoxObj).getSelectionModel().getSelectedItem();
                if (size == null) {
                    showError("Size cannot be empty");
                    return null;
                }
            }

            Object genderComboBox = vBox.getChildren().get(7);
            if (genderComboBox instanceof ComboBox) {
                gender = (Gender) ((ComboBox) genderComboBox).getSelectionModel().getSelectedItem();
                if (size == null) {
                    showError("Gender cannot be empty");
                    return null;
                }

                Object ammountObj = vBox.getChildren().get(9);
                if (ammountObj instanceof TextField) {
                    String text = ((TextField) ammountObj).getCharacters().toString();
                    try {
                        ammount = Integer.parseInt(text);

                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
            }
        }
        return new TripleCharSize(size, ammount, gender);
    }

    private TripleNumericSize getNumericQuantityFromCreation() {
        Object vBoxObj = leftVBox.getChildren().get(leftVBox.getChildren().size() - 1);
        Integer size = null;
        Gender gender = null;
        Integer ammount = -1;
        if (vBoxObj instanceof VBox) {
            VBox vBox = (VBox) vBoxObj;
            Object sizeTextField = vBox.getChildren().get(5);
            if (sizeTextField instanceof TextField) {
                size = Integer.parseInt(((TextField) sizeTextField).getCharacters().toString());
            }

            Object genderComboBox = vBox.getChildren().get(7);
            if (genderComboBox instanceof ComboBox) {
                gender = (Gender) ((ComboBox) genderComboBox).getSelectionModel().getSelectedItem();
                if (size == null) {
                    showError("Gender cannot be empty");
                    return null;
                }

                Object ammountObj = vBox.getChildren().get(9);
                if (ammountObj instanceof TextField) {
                    String text = ((TextField) ammountObj).getCharacters().toString();
                    if (text.isEmpty()) {
                        showError("Amount cannot be empty");
                        return null;
                    }
                    try {
                        ammount = Integer.parseInt(text);

                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
            }
        }
        return new TripleNumericSize(size, ammount, gender);
    }

    private void prepareShirtsCreationViews() {
        Label isThermoactiveLabel = new Label("Is thermoactive:");
        CheckBox isThermoactiveCheckBox = new CheckBox();

        Label materialLabel = new Label("Main material:");
        ComboBox<Material> materialComboBox = new ComboBox();
        materialComboBox.getItems().addAll(Material.values());


        VBox vBox = new VBox();
        vBox.getChildren().addAll(isThermoactiveLabel,
                isThermoactiveCheckBox,
                materialLabel,
                materialComboBox);
        prepareCharSizeGenderAmmount(vBox);
        leftVBox.getChildren().add(vBox);


    }

    private void showError(String errorMessage) {
        result.setText(errorMessage);
    }

    private void prepareShoesCreationViews() {
        Label shoeTypeLabel = new Label("Type:");
        ComboBox<ShoeType> shoeTypeComboBox = new ComboBox();
        shoeTypeComboBox.getItems().addAll(ShoeType.values());

        Label materialLabel = new Label("Main material:");
        ComboBox<Material> materialComboBox = new ComboBox();
        materialComboBox.getItems().addAll(Material.values());

        VBox vBox = new VBox();
        vBox.getChildren().addAll(shoeTypeLabel,
                shoeTypeComboBox,
                materialLabel,
                materialComboBox);

        prepareNumericSizeGenderAmmount(vBox);

        leftVBox.getChildren().add(vBox);

    }

    private void prepareTrousersCreationViews() {
        Label areWaterproofLabel = new Label("Are waterproof?:");
        CheckBox areWaterproofCheckBox = new CheckBox();

        Label removableLegsLabel = new Label("Removable Legs?:");
        CheckBox removableLegsCheckBox = new CheckBox();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(areWaterproofLabel,
                areWaterproofCheckBox,
                removableLegsLabel,
                removableLegsCheckBox);

        prepareCharSizeGenderAmmount(vBox);

        leftVBox.getChildren().add(vBox);
    }

    private void prepareCharSizeGenderAmmount(VBox vBox) {
        Label sizeLabel = new Label("Size:");
        ComboBox<Size> sizeComboBox = new ComboBox();
        sizeComboBox.getItems().addAll(Size.values());

        Label genderLabel = new Label("Gender:");
        ComboBox<Gender> genderComboBox = new ComboBox();
        genderComboBox.getItems().addAll(Gender.values());

        Label ammountLabel = new Label("Amount:");
        TextField ammount = new TextField();
        ammount.setMaxWidth(50);
        makeTextFieldNumeric(ammount);

        vBox.getChildren().addAll(
                sizeLabel,
                sizeComboBox,
                genderLabel,
                genderComboBox,
                ammountLabel,
                ammount
        );
    }

    private void prepareNumericSizeGenderAmmount(VBox vBox) {
        Label sizeLabel = new Label("Size:");
        TextField size = new TextField();
        size.setMaxWidth(50);
        makeTextFieldNumeric(size);

        Label genderLabel = new Label("Gender:");
        ComboBox<Gender> genderComboBox = new ComboBox();
        genderComboBox.getItems().addAll(Gender.values());

        Label ammountLabel = new Label("Amount:");
        TextField ammount = new TextField();
        ammount.setMaxWidth(50);
        makeTextFieldNumeric(ammount);

        vBox.getChildren().addAll(
                sizeLabel,
                size,
                genderLabel,
                genderComboBox,
                ammountLabel,
                ammount
        );
    }
}