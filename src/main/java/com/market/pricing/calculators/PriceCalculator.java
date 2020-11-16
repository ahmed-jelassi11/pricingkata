package com.market.pricing.calculators;

import com.market.pricing.ItemCode;
import com.market.pricing.PricingRule;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * the PriceCalculator class handles the items price rules
 * and the total price calculation
 */
public class PriceCalculator {

    /**
     * Used to store all the pricing rules by itemCode
     */
    private Map<ItemCode, PricingRule> pricingRules;


    public PriceCalculator() {
        this.pricingRules = new HashMap<>();
    }

    /**
     * Returns the item unit price by
     *
     * @param itemCode
     */
    public BigDecimal getItemPrice(ItemCode itemCode) {
        return this.getPricingRule(itemCode).getUnitPrice();
    }

    /**
     * Returns the pricing rule by
     *
     * @param itemCode
     */
    public PricingRule getPricingRule(ItemCode itemCode) {
        return this.pricingRules.get(itemCode);
    }

    /**
     * @return all the registred pricing rules
     * as {@code Map}
     */
    public Map<ItemCode, PricingRule> getPricingRules() {
        return this.pricingRules;
    }

    /**
     * Adds pricing rule for an item
     *
     * @param itemCode
     * @param pricingRule
     */
    public void addPricingRule(ItemCode itemCode, PricingRule pricingRule) {
        this.pricingRules.put(itemCode, pricingRule);
    }

    /**
     * Calculate total items price based on
     *
     * @param itemQuantities
     * @return total price as  {@code BigDecimal}
     */
    public BigDecimal calculateQuantitiesTotalPrice(Map<ItemCode, Integer> itemQuantities) {
        return itemQuantities
                .entrySet()
                .stream()
                .map(this::totalPriceForItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Calculate total items price based on
     *
     * @param itemWeighings
     * @return total price as  {@code BigDecimal}
     */
    public BigDecimal calculateWeighingsTotalPrice(Map<ItemCode, Double> itemWeighings) {
        return itemWeighings
                .entrySet()
                .stream()
                .map(this::priceForItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Calculate item price based on its weighings
     * @param itemCodeDoubleEntry
     * @return
     */
    private BigDecimal priceForItem(Map.Entry<ItemCode, Double> itemCodeDoubleEntry) {
        PricingRule pricingRule = this.getPricingRule(itemCodeDoubleEntry.getKey());
        return pricingRule.getUnitPrice().multiply(BigDecimal.valueOf(itemCodeDoubleEntry.getValue()));
    }

    /**
     * Calculates total price for a specific item
     *
     * @param itemsEntry
     * @return
     */
    private BigDecimal totalPriceForItem(Map.Entry<ItemCode, Integer> itemsEntry) {
        PricingRule pricingRule = this.getPricingRule(itemsEntry.getKey());
        return pricingRule.priceForQuantity(itemsEntry.getValue());
    }

}
