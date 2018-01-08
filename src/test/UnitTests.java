package test;

import core.ShopView;
import org.junit.jupiter.api.Test;
import product.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UnitTests {

    @Test
    public void correctCreateDescriptionOfProduct() {
        ShopView shopView = new ShopView();
        ArrayList<TripleCharSize> quantity = new ArrayList();
        quantity.add(new TripleCharSize(Size.S , 12, Gender.Female));
       Trousers trousers = new Trousers(2, 500, 2, BigDecimal.valueOf(129.99), "Walker trousers ", Category.Trousers,quantity , true, true);
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

        String result = tripleNumericSize.getStringWithoutAmmount();

        assertEquals( expectedResult.compareTo(result),0 ,"");
    }
}
