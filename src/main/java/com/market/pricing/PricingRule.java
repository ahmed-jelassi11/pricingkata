package com.market.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PricingRule {

    private final BigDecimal price;
    private final Offer offer;

    public PricingRule(BigDecimal price) {
        this(price, Offer.noOffer(price));
    }

    public PricingRule(BigDecimal price, Offer offer) {
        price.setScale(2, RoundingMode.HALF_UP);
        this.price = price;
        this.offer = offer;
    }

        public BigDecimal priceForQuantity(int quantity) {
        BigDecimal offerPrice = offer.getPrice();
        int offerGroups = quantity / offer.getQuantity();
        int outsideOffer = quantity % offer.getQuantity();
        BigDecimal totalPrice = offerPrice.multiply(BigDecimal.valueOf(offerGroups)).add(price.multiply(BigDecimal.valueOf(outsideOffer)));
        return totalPrice;
    }

    public BigDecimal getPrice() {
        return this.price;
    }
}
