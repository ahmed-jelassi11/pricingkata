package com.market.pricing.checkout;

import com.market.pricing.ItemCode;
import com.market.pricing.PricingRule;
import com.market.pricing.calculators.PriceCalculator;
import com.market.pricing.offers.GroupPricingOffer;
import com.market.pricing.offers.SpecialOffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        PriceCalculator priceCalculator = new PriceCalculator();
        Checkout checkout = new Checkout(priceCalculator);
        BigDecimal actualTotal = checkout.total();
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void test_one_product_checkout() {
        ItemCode itemCode = new ItemCode("A1");
        BigDecimal expectedTotal = new BigDecimal(0.65);
        expectedTotal = expectedTotal.setScale(2, RoundingMode.HALF_UP);
        PriceCalculator priceCalculator = new PriceCalculator();
        priceCalculator.addPricingRule(itemCode, pricingRule1);
        Checkout checkout = new Checkout(priceCalculator);
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
        PriceCalculator priceCalculator = new PriceCalculator();
        priceCalculator.addPricingRule(itemCode1,pricingRule1);
        priceCalculator.addPricingRule(itemCode2, pricingRule2);
        Checkout checkout = new Checkout(priceCalculator);
        checkout.scan(itemCode1);
        checkout.scan(itemCode2);
        BigDecimal actualTotal = checkout.total();
        actualTotal = actualTotal.setScale(2, RoundingMode.HALF_UP);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void testTotalPriceForPricingRules() {
        ItemCode itemCode1 = new ItemCode("A1");
        ItemCode itemCode2 = new ItemCode("A2");
        ItemCode itemCode3 = new ItemCode("A3");
        ItemCode itemCode4 = new ItemCode("A4");
        ItemCode itemCode5 = new ItemCode("A5");

        PriceCalculator priceCalculator = new PriceCalculator();
        priceCalculator.addPricingRule(itemCode1,
                new PricingRule(BigDecimal.ONE, new SpecialOffer(3,2)));

        priceCalculator.addPricingRule(itemCode2,
                new PricingRule(BigDecimal.TEN, new GroupPricingOffer(5, BigDecimal.valueOf(40))));

        priceCalculator.addPricingRule(itemCode3,
                new PricingRule(BigDecimal.valueOf(5), new GroupPricingOffer(3, BigDecimal.valueOf(11))));

        priceCalculator.addPricingRule(itemCode4,
                new PricingRule(BigDecimal.TEN));
        priceCalculator.getPricingRule(itemCode4).applyPercentageDiscount(30);

        priceCalculator.addPricingRule(itemCode5,
                new PricingRule(BigDecimal.TEN));

        Checkout checkout = new Checkout(priceCalculator);
        checkout.scan(itemCode1);

        assertEquals(1d, checkout.total().doubleValue());

        checkout.scan(itemCode1);
        assertEquals(2d, checkout.total().doubleValue());

        checkout.scan(itemCode2);
        assertEquals(12d, checkout.total().doubleValue());

        checkout.scan(itemCode3);
        assertEquals(17d, checkout.total().doubleValue());

        checkout.scan(itemCode1);
        assertEquals(17d, checkout.total().doubleValue());
        checkout.scan(itemCode1);

        assertEquals(18d, checkout.total().doubleValue());

        checkout.scan(itemCode4);
        assertEquals(25d, checkout.total().doubleValue());

        checkout.scan(itemCode5, 0.6);
        checkout.scan(itemCode5, 0.9);

        assertEquals(40d, checkout.total().doubleValue());

    }


}
