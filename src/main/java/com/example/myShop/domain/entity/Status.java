package com.example.myShop.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

/**
 * @author nafis
 * @since 28.12.2021
 */

public enum Status {
    STATUS1("IN_STOCK"),
    STATUS2("CANCELED"),
    STATUS3("DELIVERED");

    private String status;

    Status(String name) {
        this.status = name;
    }

    @JsonCreator
    public static Status decode(final String status) {
        return Stream.of(Status.values()).filter(targetEnum -> targetEnum.status.equals(status)).findFirst().orElse(null);
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
