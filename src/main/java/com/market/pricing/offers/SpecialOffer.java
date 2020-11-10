package com.market.pricing.offers;

import java.math.BigDecimal;

public class SpecialOffer extends Offer {
    /**
     * Used to store the number of items priced for @quantity
     */
    private final int forPriceOf;


    public SpecialOffer(int quantity, int forPriceOf) {
        this.quantity = quantity;
        this.forPriceOf = forPriceOf;
    }

    /**
     * Calculates the total price of a given
     * @param quantity of items
     * @param unitPrice
     * @return the price as {@code BigDecimal}
     */
    @Override
    public BigDecimal priceForQuantity(int quantity, BigDecimal unitPrice) {
        int offerGroups = quantity / this.quantity;
        int outsideOffer = quantity % this.quantity;
        return unitPrice.multiply(BigDecimal.valueOf(offerGroups))
                .multiply(BigDecimal.valueOf(this.forPriceOf))
                .add(unitPrice.multiply(BigDecimal.valueOf(outsideOffer)));
    }
}
