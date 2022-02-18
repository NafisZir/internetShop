package com.example.myShop.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author nafis
 * @since 28.12.2021
 */

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    CREATING(1),
    PENDING(2),
    ASSEMBLY(3),
    IN_STOCK(4),
    IN_TRANSIT(5),
    AWAITING_PICKUP(6),
    COMPLETED(8),
    CANCELLED(-1);

    private final int number;

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
