package com.market.pricing;

import java.math.BigDecimal;

public class Offer {

    private final int quantity;
    private final BigDecimal price;

    public Offer(int quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static Offer noOffer(BigDecimal price) {
        return new Offer(1, price);
    }
}
