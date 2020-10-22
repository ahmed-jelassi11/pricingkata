package com.market.pricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingRulesTest {

    PricingRules pricingRules;
    ItemCode itemCode;

    @BeforeEach
    void init() {
        itemCode = new ItemCode("A1");
        pricingRules = new PricingRules();
    }

    @Test
    void test_add_pricingRule() {
        pricingRules.add(itemCode, BigDecimal.valueOf(0.65));
        assertEquals(1, pricingRules.size());
    }

    @Test
    void test_get_item_price() {
        BigDecimal expectedPrice = new BigDecimal(0.65);
        pricingRules.add(itemCode, new BigDecimal(0.65));
        BigDecimal actualPrice = pricingRules.get(itemCode);
        assertEquals(expectedPrice, actualPrice);
    }
}
