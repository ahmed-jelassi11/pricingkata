package com.market.pricing;

import java.math.BigDecimal;

public class Checkout {

    private BigDecimal total = BigDecimal.ZERO;
    private PricingRules pricingRules;

    public Checkout(PricingRules pricingRules) {
        this.pricingRules = pricingRules;
    }

    public BigDecimal total() {
        return total;
    }

    public void scan(ItemCode itemCode) {
        total = total.add(pricingRules.get(itemCode));
    }
}
