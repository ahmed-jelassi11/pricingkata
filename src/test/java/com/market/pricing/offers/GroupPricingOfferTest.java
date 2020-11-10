package com.market.pricing.offers;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class GroupPricingOfferTest {

    @Test
    void test_price_for_quantity() {
        GroupPricingOffer groupPricingOffer = new GroupPricingOffer(3, BigDecimal.TEN);
        BigDecimal actualPrice = groupPricingOffer.priceForQuantity(9, BigDecimal.valueOf(5));
        BigDecimal expected = BigDecimal.valueOf(30);
        assertEquals(expected, actualPrice);
    }
}
