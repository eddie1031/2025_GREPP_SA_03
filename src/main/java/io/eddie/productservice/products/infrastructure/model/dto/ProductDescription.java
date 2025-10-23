package io.eddie.productservice.products.infrastructure.model.dto;

public record ProductDescription(
        String code,
        String name,
        String description,
        Long price
) {
}
