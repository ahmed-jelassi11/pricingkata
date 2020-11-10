package com.market.pricing.calculators;

import com.market.pricing.ItemCode;
import com.market.pricing.PricingRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceCalculatorTest {

    PriceCalculator priceCalculator;
    PricingRule pricingRule;
    ItemCode itemCode;
    BigDecimal price = BigDecimal.valueOf(0.65);

    @BeforeEach
    void init() {
        itemCode = new ItemCode("A1");
        priceCalculator = new PriceCalculator();
        pricingRule = new PricingRule(price);
    }

    @Test
    void test_add_pricingRule() {
        priceCalculator.addPricingRule(itemCode, pricingRule);
        assertEquals(1, priceCalculator.getPricingRules().size());
    }

    @Test
    void test_get_item_price() {
        BigDecimal expectedPrice = price;
        expectedPrice = expectedPrice.setScale(2, RoundingMode.HALF_UP);
        priceCalculator.addPricingRule(itemCode, pricingRule);
        BigDecimal actualPrice = priceCalculator.getItemPrice(itemCode);
        assertEquals(expectedPrice, actualPrice);
    }
}
