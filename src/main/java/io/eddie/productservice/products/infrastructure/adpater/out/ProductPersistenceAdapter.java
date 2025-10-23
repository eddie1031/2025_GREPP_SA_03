package io.eddie.productservice.products.infrastructure.adpater.out;

import io.eddie.productservice.products.application.port.out.ProductPersistencePort;
import io.eddie.productservice.products.domain.model.Product;
import io.eddie.productservice.products.domain.vo.pagination.PageQuery;
import io.eddie.productservice.products.domain.vo.pagination.PageResult;
import io.eddie.productservice.products.infrastructure.mapper.ProductMapper;
import io.eddie.productservice.products.infrastructure.model.persistence.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product save(Product product) {

        ProductEntity entity = ProductMapper.toEntity(product);

        productJpaRepository.save(entity);

        return null;
    }

    @Override
    public Optional<Product> getProductByCode(String code) {
        return Optional.empty();
    }

    @Override
    public PageResult<Product> findAll(PageQuery pageQuery) {
        return null;
    }
}
