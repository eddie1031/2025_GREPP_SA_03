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

    public static void applyToEntity(Product domain, ProductEntity entity) {

        ProductSpecification specification = domain.getSpecification();

        entity.setName(specification.name());
        entity.setDescription(specification.description());
        entity.setPrice(specification.price());

        entity.updateClock();

    }

    public static Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getCode(),
                new ProductSpecification(entity.getName(), entity.getDescription(), entity.getPrice()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

}
