package com.example.myShop.integration;

import com.example.myShop.domain.dto.selectedProduct.SelectedProductCreateDto;
import com.example.myShop.domain.dto.selectedProduct.SelectedProductDto;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author nafis
 * @since 22.02.2022
 */
public class SelectedProductControllerTest extends BaseTest{
    @Test
    public void testCreateSelectedProduct(){
        SelectedProductCreateDto request = getClassPathResourceAsObject("/request/selectedProduct/create_selected_product.json",
                new TypeReference<>() {
                });
        SelectedProductDto excepted = getClassPathResourceAsObject("/excepted/selectedProduct/created_selected_product.json",
                new TypeReference<>(){
                });

        webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("users/1/selected-products").queryParam("goodsId", 1).build())
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SelectedProductDto.class)
                .value(result -> assertThat(result)
                        .as("")
                        .usingRecursiveComparison()
                        .ignoringFields("id", "order")
                        .isEqualTo(excepted)
                );
    }
}
