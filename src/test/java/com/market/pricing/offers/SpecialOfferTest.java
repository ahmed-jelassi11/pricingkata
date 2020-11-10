package com.market.pricing.offers;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpecialOfferTest {

    @Test
    void test_price_for_quantity() {
        SpecialOffer specialOffer = new SpecialOffer(4, 2);
        BigDecimal actualPrice = specialOffer.priceForQuantity(9, BigDecimal.TEN);
        BigDecimal expected = BigDecimal.valueOf(50);
        assertEquals(expected, actualPrice);
    }
}
