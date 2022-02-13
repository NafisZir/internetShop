package com.example.myShop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.Collection;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 13.02.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor
public class CollectionWrapperDto<T> {
    Collection<T> collection;
}
