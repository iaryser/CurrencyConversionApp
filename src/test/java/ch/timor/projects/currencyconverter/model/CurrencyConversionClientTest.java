package ch.timor.projects.currencyconverter.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyConversionClientTest {

    private String positiveMock = """
            {
                "success": "true",
                "query": {
                    "from": "USD",
                    "to": "GBP",
                    "amount": "10"
                },
                "info": {
                    "quote": "0.658443"
                },
                "result": "6.58443"
            }
            """;

    private String negativeMock = """
            {
                "success": "false",
                "query": {
                },
                "info": {
                },
                "result": ""
            }
            """;

    @Test
    public void testParsingOfPositiveResponse() {
        CurrencyConversionClient client = new CurrencyConversionClient();
        ConversionResponse res = client.parseResponse(positiveMock);
        assertEquals("true", res.getSuccess());
    }

    @Test
    public void testParsingOfPositiveResponse2() {
        CurrencyConversionClient client = new CurrencyConversionClient();
        ConversionResponse res = client.parseResponse(positiveMock);
        assertEquals("USD", res.getQuery().getFrom());
    }

    @Test
    public void testParsingOfPositiveResponse3() {
        CurrencyConversionClient client = new CurrencyConversionClient();
        ConversionResponse res = client.parseResponse(positiveMock);
        assertEquals("GBP", res.getQuery().getTo());
    }

    @Test
    public void testParsingOfPositiveResponse4() {
        CurrencyConversionClient client = new CurrencyConversionClient();
        ConversionResponse res = client.parseResponse(positiveMock);
        assertEquals("10", res.getQuery().getAmount());
    }

    @Test
    public void testParsingOfPositiveResponse5() {
        CurrencyConversionClient client = new CurrencyConversionClient();
        ConversionResponse res = client.parseResponse(positiveMock);
        assertEquals("0.658443", res.getInfo().getQuote());
    }

    @Test
    public void testParsingOfPositiveResponse6() {
        CurrencyConversionClient client = new CurrencyConversionClient();
        ConversionResponse res = client.parseResponse(positiveMock);
        assertEquals("6.58443", res.getResult());
    }

    @Test
    public void testParsingOfNegativeResponse() {
        CurrencyConversionClient client = new CurrencyConversionClient();
        ConversionResponse res = client.parseResponse(negativeMock);
        assertEquals("false", res.getSuccess());
    }

    @Test
    public void testParsingOfNegativeResponse2() {
        CurrencyConversionClient client = new CurrencyConversionClient();
        ConversionResponse res = client.parseResponse(negativeMock);
        assertEquals(null, res.getQuery().getFrom());
    }

    @Test
    public void testParsingOfNegativeResponse3() {
        CurrencyConversionClient client = new CurrencyConversionClient();
        ConversionResponse res = client.parseResponse(negativeMock);
        assertEquals(null, res.getQuery().getTo());
    }

    @Test
    public void testParsingOfNegativeResponse4() {
        CurrencyConversionClient client = new CurrencyConversionClient();
        ConversionResponse res = client.parseResponse(negativeMock);
        assertEquals(null, res.getQuery().getAmount());
    }

    @Test
    public void testParsingOfNegativeResponse5() {
        CurrencyConversionClient client = new CurrencyConversionClient();
        ConversionResponse res = client.parseResponse(negativeMock);
        assertEquals(null, res.getInfo().getQuote());
    }

    @Test
    public void testParsingOfNegativeResponse6() {
        CurrencyConversionClient client = new CurrencyConversionClient();
        ConversionResponse res = client.parseResponse(negativeMock);
        assertEquals("", res.getResult());
    }

}