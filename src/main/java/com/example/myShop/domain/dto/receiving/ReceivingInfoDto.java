package com.example.myShop.domain.dto.receiving;

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
public class ReceivingInfoDto {
    int id;
    String receiveMethod;
    String address;
}
