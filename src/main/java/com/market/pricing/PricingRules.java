package com.market.pricing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PricingRules {

    private Map<ItemCode, BigDecimal> pricingRules;

    public PricingRules() {
        this.pricingRules = new HashMap<>();
    }

    public BigDecimal get(ItemCode itemCode) {
        return this.pricingRules.get(itemCode);
    }

    public void add(ItemCode itemCode, BigDecimal unitPrice) {
        this.pricingRules.put(itemCode, unitPrice);
    }

    public int size() {
        return pricingRules.size();
    }
}
