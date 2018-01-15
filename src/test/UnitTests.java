package test;

import core.ShopController;
import core.ShopModel;
import core.ShopView;
import org.junit.Before;
import org.junit.Test;
import product.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTests {

    private ShopView shopView;
    private ArrayList<TripleCharSize> quantity;
    private Trousers trousers;
    private ShopController shopController;
    private ShopModel shopModel;

    @Before
    public void initObjects() {
        shopView = new ShopView();
        quantity = new ArrayList();
        quantity.add(new TripleCharSize(Size.S , 12, Gender.Female));
        trousers = new Trousers(2, 500, 2, BigDecimal.valueOf(129.99), "Walker trousers ", Category.Trousers,quantity , true, true);

    }

    @Test
    public void correctCreateDescriptionOfProduct() {

        String expectedString = "Name: Walker trousers \n" +
                "Category: Trousers\n" +
                "Weight: 500 g\n" +
                "Price: 129.99 $\n" +
                "Waterproof\n" +
                "Removable legs\n" +
                "--------------\n" +
                "Available sizes: \n" +
                "Female S 12 \n";

        String resultString = shopView.createDescriptionOfProduct(trousers);

        assertEquals( expectedString.compareTo(resultString),0 ,"");
    }

    @Test
    public void correctConversionForNumericTriple() {
        TripleNumericSize tripleNumericSize = new TripleNumericSize( 39, 1, Gender.Unisex);
        String expectedResult = "Unisex 39";

        String result = tripleNumericSize.getStringWithoutAmount();

        assertEquals( expectedResult.compareTo(result),0 ,"");
    }
}
