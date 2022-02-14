package com.example.myShop.domain.enums;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author nafis
 * @since 28.12.2021
 */

public enum OrderStatus {
    PENDING,
    IN_STOCK,
    ASSEMBLY,
    CREATING,
    CANCELLED,
    COMPLETED,
    IN_TRANSIT,
    AWAITING_PICKUP;

    public static Collection<OrderStatus> getNonActiveStatuses(){
        Collection<OrderStatus> collection = new HashSet<>();
        collection.add(CREATING);
        collection.add(CANCELLED);
        collection.add(COMPLETED);
        return collection;
    }

    public static Collection<OrderStatus> getActiveStatuses(){
        Collection<OrderStatus> collection = new HashSet<>();
        collection.add(PENDING);
        collection.add(IN_STOCK);
        collection.add(ASSEMBLY);
        collection.add(IN_TRANSIT);
        collection.add(AWAITING_PICKUP);
        return collection;
    }
}
