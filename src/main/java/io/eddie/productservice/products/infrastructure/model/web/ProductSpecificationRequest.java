package io.eddie.productservice.products.infrastructure.model.web;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProductSpecificationRequest(

        @NotBlank
        String name,

        @NotBlank
        String description,

        @Min(value = 1000L)
        Long price

) {
}
