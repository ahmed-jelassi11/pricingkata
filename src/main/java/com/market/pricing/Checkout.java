package com.market.pricing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Checkout {

    private PricingRules pricingRules;
    private final Map<ItemCode, Integer> itemQuantities;

    public Checkout(PricingRules pricingRules) {
        this.pricingRules = pricingRules;
        this.itemQuantities = new HashMap<>();
    }

    public void scan(ItemCode itemCode) {
        if (pricingRules.contains(itemCode)) {
            itemQuantities.putIfAbsent(itemCode, 0);
            itemQuantities.compute(itemCode, (item, quantity) -> ++quantity);
        }
    }

    public BigDecimal calculateTotalPrice() {
        return itemQuantities
                .entrySet()
                .stream()
                .map(this::totalPriceForItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal totalPriceForItem(Map.Entry<ItemCode, Integer> itemsEntry) {
        PricingRule pricingRule = pricingRules.getPricingRule(itemsEntry.getKey());
        return pricingRule.priceForQuantity(itemsEntry.getValue());
    }
}

