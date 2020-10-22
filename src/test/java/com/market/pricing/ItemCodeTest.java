package com.market.pricing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ItemCodeTest {

    @Test
    public void test_equality() {
        ItemCode itemCode1 = new ItemCode("A1");
        ItemCode itemCode2 = new ItemCode("A1");
        assertEquals(itemCode1, itemCode2);
    }

    @Test
    public void test_inequality() {
        ItemCode itemCode1 = new ItemCode("A1");
        ItemCode itemCode2 = new ItemCode("A2");
        assertNotEquals(itemCode1, itemCode2);
    }

}
