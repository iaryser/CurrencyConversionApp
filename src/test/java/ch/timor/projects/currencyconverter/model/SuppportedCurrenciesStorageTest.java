package ch.timor.projects.currencyconverter.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuppportedCurrenciesStorageTest {
    //!---MOCK-STRING-SETUP---!
    private String positiveMock = """
        {
          "success": "true",
          "currencies": {
            "AED": "United Arab Emirates Dirham",
            "AFN": "Afghan Afghani",
            "ALL": "Albanian Lek"
          }
        }
        """;

    private String negativeMock = """
        {
          "success": "false",
          "currencies": { }
        }
        """;

    @Test
    public void testFillMapWithSupportedCurrencies1() {
        SupportedCurrenciesStorage suppColl = new SupportedCurrenciesStorage(positiveMock);
        assertEquals(3, suppColl.getAmountOfSupportedCurrencies());
    }

    @Test
    public void testFillMapWithSupportedCurrencies2() {
        SupportedCurrenciesStorage suppColl = new SupportedCurrenciesStorage(negativeMock);
        assertEquals(0, suppColl.getAmountOfSupportedCurrencies());
    }

    @Test
    public void testFillMapWithSupportedCurrencies3() {
        SupportedCurrenciesStorage suppColl = new SupportedCurrenciesStorage("");
        assertEquals(0, suppColl.getAmountOfSupportedCurrencies());
    }

    @Test
    public void testFillMapWithSupportedCurrencies4() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            SupportedCurrenciesStorage suppColl = new SupportedCurrenciesStorage(null);
        });
        assertEquals("Json is null!", e.getMessage());
    }
}