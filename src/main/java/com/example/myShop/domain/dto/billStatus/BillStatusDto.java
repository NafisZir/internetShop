package com.example.myShop.domain.dto.billStatus;

import com.example.myShop.domain.enums.BillStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 13.02.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
@Schema(name = "BillStatus", description = "Name of billing status")
public class BillStatusDto {
    @Schema(description = "Billing status name",
            required = true)
    BillStatus billStatus;
}
