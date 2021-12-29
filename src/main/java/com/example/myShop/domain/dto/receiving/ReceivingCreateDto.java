package com.example.myShop.domain.dto.receiving;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class ReceivingCreateDto {
    @NotBlank(message = "{receiving.receiveMethod.empty}")
    @Size(max = 20)
    String receiveMethod;

    @NotBlank(message = "{receiving.address.empty}")
    @Size(max = 100)
    String address;
}
