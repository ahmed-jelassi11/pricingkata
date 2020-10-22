package com.market.pricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingRulesTest {

    PricingRules pricingRules;
    PricingRule pricingRule;
    ItemCode itemCode;

    @BeforeEach
    void init() {
        itemCode = new ItemCode("A1");
        pricingRules = new PricingRules();
        pricingRule = new PricingRule(BigDecimal.valueOf(0.65));
    }

    @Test
    void test_add_pricingRule() {
        pricingRules.add(itemCode, pricingRule);
        assertEquals(1, pricingRules.size());
    }

    @Test
    void test_get_item_price() {
        BigDecimal expectedPrice = new BigDecimal(0.65);
        expectedPrice = expectedPrice.setScale(2, RoundingMode.HALF_UP);
        pricingRules.add(itemCode, pricingRule);
        BigDecimal actualPrice = pricingRules.getItemPrice(itemCode);
        assertEquals(expectedPrice, actualPrice);
    }
}
