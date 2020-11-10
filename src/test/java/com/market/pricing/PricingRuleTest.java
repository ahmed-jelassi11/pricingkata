package com.market.pricing;

import com.market.pricing.offers.GroupPricingOffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingRuleTest {

    private PricingRule pricingRule;
    private PricingRule offerPricingRule;

    @BeforeEach
    public void init() {
        pricingRule = new PricingRule(BigDecimal.valueOf(500));
        offerPricingRule = new PricingRule(BigDecimal.valueOf(500),
                new GroupPricingOffer(3, BigDecimal.valueOf(1000)));
    }

    @Test
    public void test_pricing() {
        assertEquals(BigDecimal.valueOf(500), pricingRule.getUnitPrice());
    }

    @Test
    public void test_amount_discount() {
        pricingRule.applyAmountDiscount(BigDecimal.TEN);
        BigDecimal expected = BigDecimal.valueOf(490);
        BigDecimal actual = pricingRule.getUnitPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void test_percentage_discount() {
        pricingRule.applyPercentageDiscount(10);
        BigDecimal expected = BigDecimal.valueOf(450d);
        BigDecimal actual = pricingRule.getUnitPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void test_price_for_quantity() {
        BigDecimal actual = offerPricingRule.priceForQuantity(5);
        BigDecimal expected = BigDecimal.valueOf(2000);
        assertEquals(expected, actual);
    }
}
