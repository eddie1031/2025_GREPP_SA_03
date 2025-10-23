package io.eddie.productservice.products.infrastructure.mapper;

import io.eddie.productservice.products.domain.model.Product;
import io.eddie.productservice.products.domain.vo.ProductSpecification;
import io.eddie.productservice.products.infrastructure.model.persistence.ProductEntity;

public class ProductMapper {

    public static ProductEntity toEntity(Product domain) {

        ProductSpecification spec = domain.getSpecification();

        return ProductEntity.builder()
                .code(domain.getCode())
                .name(spec.name())
                .description(spec.description())
                .price(spec.price())
                .build();

    }

}
