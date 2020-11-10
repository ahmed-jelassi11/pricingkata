package com.market.pricing;

import com.market.pricing.offers.Offer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

/**
 * Pricing Rule provide the handling
 * the offer and the price of a given item
 */
public class PricingRule {

    /**
     * Used for storing the item's unit price
     */
    private BigDecimal unitPrice;

    /**
     * Used for storing the item's offer
     */
    private Offer offer;

    /**
     * Constructor of a pricing rule without offer
     * @param unitPrice
     */
    public PricingRule(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Construtor that accepts unit price and offer
     * @param unitPrice
     * @param offer
     */
    public PricingRule(BigDecimal unitPrice, Offer offer) {
        unitPrice.setScale(2, RoundingMode.HALF_UP);
        this.unitPrice = unitPrice;
        this.offer = offer;
    }

    /**
     * Accepts the item quantity and returns its total price
     * @param quantity
     * @return
     */
    public BigDecimal priceForQuantity(int quantity) {
        return Optional.ofNullable(this.offer)
                .map(offer -> offer.priceForQuantity(quantity, unitPrice))
                .orElse(this.getUnitPrice().multiply(BigDecimal.valueOf(quantity)));
    }

    /**
     * apply discount by amount directly
     * @param discount
     */
    public void applyAmountDiscount(BigDecimal discount) {
        this.unitPrice = unitPrice.subtract(discount);
    }

    /**
     * apply percentage discount to the pricing rule
     * @param discount
     */
    public void applyPercentageDiscount(int discount) {
        double percentage = (double) (100 - discount) / 100;
        this.unitPrice = unitPrice.multiply(BigDecimal.valueOf(percentage));
    }

    /**
     * @return unit price of the pricing rule
     */
    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }
}
