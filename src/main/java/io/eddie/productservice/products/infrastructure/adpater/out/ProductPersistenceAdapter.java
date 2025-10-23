package io.eddie.productservice.products.infrastructure.adpater.out;

import io.eddie.productservice.products.application.port.out.ProductPersistencePort;
import io.eddie.productservice.products.domain.model.Product;
import io.eddie.productservice.products.domain.vo.pagination.PageQuery;
import io.eddie.productservice.products.domain.vo.pagination.PageResult;
import io.eddie.productservice.products.infrastructure.mapper.ProductMapper;
import io.eddie.productservice.products.infrastructure.model.persistence.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product save(Product product) {

        ProductEntity productEntity = productJpaRepository.findByCode(product.getCode())
                .orElseGet(() -> productJpaRepository.save(ProductMapper.toEntity(product)));

        ProductMapper.applyToEntity(product, productEntity);

        return ProductMapper.toDomain(productEntity);

    }

    @Override
    public Optional<Product> getProductByCode(String code) {
        return productJpaRepository.findByCode(code)
                .map(ProductMapper::toDomain);
    }

    @Override
    public PageResult<Product> findAll(PageQuery pageQuery) {

        Pageable pageable = toPageable(pageQuery);

        Page<ProductEntity> productPage = productJpaRepository.findAll(pageable);

        return new PageResult<>(
                productPage.getContent()
                        .stream()
                        .map(ProductMapper::toDomain)
                        .toList(),
                pageQuery.page(),
                pageQuery.size(),
                productPage.getNumberOfElements(),
                productPage.hasNext()
        );

    }

    private Pageable toPageable(PageQuery pageQuery) {

        Sort sort = switch (pageQuery.sort().direction()) {
            case ASC -> Sort.by(pageQuery.sort().property()).ascending();
            case DESC -> Sort.by(pageQuery.sort().property()).descending();
        };

        return PageRequest.of(pageQuery.page(), pageQuery.size(), sort);
    }

}
