package com.market.pricing.offers;

import java.math.BigDecimal;

/**
 * the Offer class is an abstract representation of the Offers
 */
public abstract class Offer {

    /**
     * Used to store offer's applicable items number
     */
    int quantity;

    /**
     * Calculates the total price of a given
     * @param quantity of items
     * @param unitPrice
     * @return the price as {@code BigDecimal}
     */
    public abstract BigDecimal priceForQuantity(int quantity,BigDecimal unitPrice);
}
