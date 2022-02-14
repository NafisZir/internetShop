package com.example.myShop.domain.dto.collectionWrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Collection;

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
