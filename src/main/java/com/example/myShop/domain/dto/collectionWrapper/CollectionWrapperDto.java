package com.example.myShop.domain.dto.collectionWrapper;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "CollectionWrapper", description = "It wrap any class that implements Collection interface to Json")
public class CollectionWrapperDto<T> {
    @Schema(description = "Content for wrap",
            required = true)
    Collection<T> collection;
}
