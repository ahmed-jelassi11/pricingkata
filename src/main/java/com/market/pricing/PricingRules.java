package com.market.pricing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PricingRules {

    private Map<ItemCode, PricingRule> pricingRules;

    public PricingRules() {
        this.pricingRules = new HashMap<>();
    }

    public BigDecimal getItemPrice(ItemCode itemCode) {
        return this.getPricingRule(itemCode).getPrice();
    }

    public PricingRule getPricingRule(ItemCode itemCode){
        return this.pricingRules.get(itemCode);
    }

    public void add(ItemCode itemCode, PricingRule pricingRule) {
        this.pricingRules.put(itemCode, pricingRule);
    }

    public int size() {
        return pricingRules.size();
    }

    public boolean contains(ItemCode itemCode){
        return pricingRules.containsKey(itemCode);
    }
}
