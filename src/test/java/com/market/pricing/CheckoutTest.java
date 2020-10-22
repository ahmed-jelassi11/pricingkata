package com.market.pricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest {


    PricingRule pricingRule1;
    PricingRule pricingRule2;
    @BeforeEach
    public void init(){
        pricingRule1 = new PricingRule(BigDecimal.valueOf(0.65));
        pricingRule2 = new PricingRule(BigDecimal.valueOf(1));
    }

    @Test
    public void test_checkout_without_items() {
        BigDecimal expectedTotal = BigDecimal.ZERO;
        PricingRules pricingRules = new PricingRules();
        Checkout checkout = new Checkout(pricingRules);
        BigDecimal actualTotal = checkout.calculateTotalPrice();
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void test_one_product_checkout() {
        ItemCode itemCode = new ItemCode("A1");
        BigDecimal expectedTotal = new BigDecimal(0.65);
        expectedTotal = expectedTotal.setScale(2, RoundingMode.HALF_UP);
        PricingRules pricingRules = new PricingRules();
        pricingRules.add(itemCode, pricingRule1);
        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(itemCode);
        BigDecimal actualTotal = checkout.calculateTotalPrice();
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void test_two_product_checkout() {
        ItemCode itemCode1 = new ItemCode("A1");
        ItemCode itemCode2 = new ItemCode("A2");
        BigDecimal expectedTotal = new BigDecimal(1.65);
        expectedTotal = expectedTotal.setScale(2, RoundingMode.HALF_UP);
        PricingRules pricingRules = new PricingRules();
        pricingRules.add(itemCode1,pricingRule1);
        pricingRules.add(itemCode2, pricingRule2);
        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(itemCode1);
        checkout.scan(itemCode2);
        BigDecimal actualTotal = checkout.calculateTotalPrice();
        actualTotal = actualTotal.setScale(2, RoundingMode.HALF_UP);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void testTotalPriceForPricingRules() {
        ItemCode itemCode1 = new ItemCode("A1");
        ItemCode itemCode2 = new ItemCode("A2");
        ItemCode itemCode3 = new ItemCode("A3");
        PricingRules pricingRules = new PricingRules();
        pricingRules.add(itemCode1,
                new PricingRule(BigDecimal.valueOf(0.65), new Offer(3, BigDecimal.valueOf(1.49))));

        pricingRules.add(itemCode2,
                new PricingRule(BigDecimal.valueOf(9.99), new Offer(5, BigDecimal.valueOf(39.99))));

        pricingRules.add(itemCode3,
                new PricingRule(BigDecimal.valueOf(4.99), new Offer(3, BigDecimal.valueOf(10.99))));

        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(itemCode1);

        assertEquals(0.65d, checkout.calculateTotalPrice().doubleValue());

        checkout.scan(itemCode1);
        assertEquals(1.30d, checkout.calculateTotalPrice().doubleValue());

        checkout.scan(itemCode2);
        assertEquals(11.29d, checkout.calculateTotalPrice().doubleValue());

        checkout.scan(itemCode3);
        assertEquals(16.28d, checkout.calculateTotalPrice().doubleValue());

        checkout.scan(itemCode1);
        checkout.scan(itemCode1);

        assertEquals(17.12d, checkout.calculateTotalPrice().doubleValue());

    }


}
