package com.market.pricing;

import java.util.Objects;

public class ItemCode {

    private String itemCode;

    public ItemCode(String itemCode){
        this.itemCode = itemCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCode itemCode1 = (ItemCode) o;
        return Objects.equals(itemCode, itemCode1.itemCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode);
    }
}
