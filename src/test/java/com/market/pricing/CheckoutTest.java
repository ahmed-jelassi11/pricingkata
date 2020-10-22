package com.market.pricing;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest {

    @Test
    public void test_checkout_without_items() {
        BigDecimal expectedTotal = BigDecimal.ZERO;
        PricingRules pricingRules = new PricingRules();
        Checkout checkout = new Checkout(pricingRules);
        BigDecimal actualTotal = checkout.total();
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void test_one_product_checkout() {
        ItemCode itemCode = new ItemCode("A1");
        BigDecimal expectedTotal = new BigDecimal(0.65);
        PricingRules pricingRules = new PricingRules();
        pricingRules.add(itemCode, new BigDecimal(0.65));
        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(itemCode);
        BigDecimal actualTotal = checkout.total();
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void test_two_product_checkout() {
        ItemCode itemCode1 = new ItemCode("A1");
        ItemCode itemCode2 = new ItemCode("A2");
        BigDecimal expectedTotal = new BigDecimal(1.65);
        expectedTotal = expectedTotal.setScale(2, RoundingMode.HALF_UP);
        PricingRules pricingRules = new PricingRules();
        pricingRules.add(itemCode1, new BigDecimal(0.65));
        pricingRules.add(itemCode2, new BigDecimal(1));
        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(itemCode1);
        checkout.scan(itemCode2);
        BigDecimal actualTotal = checkout.total();
        actualTotal = actualTotal.setScale(2, RoundingMode.HALF_UP);
        assertEquals(expectedTotal, actualTotal);
    }


}
