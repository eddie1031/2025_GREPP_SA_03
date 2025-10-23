package io.eddie.productservice.products.application.service;

import io.eddie.productservice.products.application.port.in.ProductCrudUseCase;
import io.eddie.productservice.products.application.port.out.ProductPersistencePort;
import io.eddie.productservice.products.domain.exception.CouldNotFindProductException;
import io.eddie.productservice.products.domain.model.Product;
import io.eddie.productservice.products.domain.vo.ProductSpecification;
import io.eddie.productservice.products.domain.vo.pagination.PageQuery;
import io.eddie.productservice.products.domain.vo.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductApplication implements ProductCrudUseCase {

    private final ProductPersistencePort productPersistencePort;

    @Override
    @Transactional
    public Product enroll(ProductSpecification specification) {

        Product product = new Product(specification);

        return productPersistencePort.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getProductByCode(String code) {
        return productPersistencePort.getProductByCode(code);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<Product> findAll(PageQuery pageQuery) {
        return productPersistencePort.findAll(pageQuery);
    }

    @Override
    @Transactional
    public Product updateSpecification(String code, ProductSpecification specification) {

        Optional<Product> productOptional = productPersistencePort.getProductByCode(code);

        if ( productOptional.isEmpty() )
            throw new CouldNotFindProductException("해당 상품을 찾을 수가 없습니다.");

        Product product = productOptional.get();
        product.changeSpecification(specification);

        product = productPersistencePort.save(product);

        return product;

    }

}
