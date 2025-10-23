package io.eddie.productservice.products.domain.vo;

public record ProductSpecification(
        String name,
        String description,
        Long price
) {
}
