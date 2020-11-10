package com.market.pricing.checkout;

import com.market.pricing.ItemCode;
import com.market.pricing.calculators.PriceCalculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * the Checkout class provides scanning items for
 * total price calculation.
 */
public class Checkout {

    private PriceCalculator priceCalculator;
    private final Map<ItemCode, Integer> itemQuantities;

    /**
     * Trusted package private constructor accepting
     * @param priceCalculator handling the price calculation
     */
    Checkout(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
        this.itemQuantities = new HashMap<>();
    }

    /**
     * Scans and computes item quantity
     * @param itemCode
     */
    public void scan(ItemCode itemCode) {
        if (priceCalculator.getPricingRules().containsKey(itemCode)) {
            itemQuantities.putIfAbsent(itemCode, 0);
            itemQuantities.compute(itemCode, (item, quantity) -> ++quantity);
        }
    }

    /**
     * calculate the price of the actual scanned items
     * @return the total calculated price as {@code BigDecimal}
     */
    public BigDecimal total(){
        return priceCalculator.calculateTotalPrice(itemQuantities);
    }
}

