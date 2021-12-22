package com.example.myShop.domain.dto.producer;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

/**
 * @author nafis
 * @since 22.12.2021
 */

@Value
@Setter
@Getter
@Jacksonized
@AllArgsConstructor
public class ProducerInfoDto {
    String name;
    String country;
}
