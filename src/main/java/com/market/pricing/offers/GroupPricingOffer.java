package com.market.pricing.offers;

import java.math.BigDecimal;

/**
 * the GroupPricingOffer provides fixed price for a fixed number of items
 */
public class GroupPricingOffer extends Offer {


    /**
     * Used to store the items group price
     */
    private final BigDecimal groupPrice;

    public GroupPricingOffer(int quantity, BigDecimal groupPrice) {
        this.quantity = quantity;
        this.groupPrice = groupPrice;
    }

    /**
     * Calculates the total price of a given
     * @param quantity of items
     * @param unitPrice
     * @return the price as {@code BigDecimal}
     */
    @Override
    public BigDecimal priceForQuantity(int quantity, BigDecimal unitPrice) {
        BigDecimal offerPrice = this.groupPrice;
        int offerGroups = quantity / this.quantity;
        int outsideOffer = quantity % this.quantity;
        BigDecimal totalPrice = offerPrice.multiply(BigDecimal.valueOf(offerGroups))
                .add(unitPrice.multiply(BigDecimal.valueOf(outsideOffer)));
        return totalPrice;
    }
}
