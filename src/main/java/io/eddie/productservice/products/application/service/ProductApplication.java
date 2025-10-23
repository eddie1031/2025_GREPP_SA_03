package io.eddie.productservice.products.application.service;

import io.eddie.productservice.products.application.port.in.ProductCrudUseCase;
import io.eddie.productservice.products.application.port.out.ProductPersistencePort;
import io.eddie.productservice.products.domain.model.Product;
import io.eddie.productservice.products.domain.vo.ProductSpecification;
import io.eddie.productservice.products.domain.vo.pagination.PageQuery;
import io.eddie.productservice.products.domain.vo.pagination.PageResult;

import java.util.List;
import java.util.Optional;

public class ProductApplication implements ProductCrudUseCase {

    private ProductPersistencePort productPersistencePort;

    @Override
    public Product enroll(ProductSpecification specification) {

        Product product = new Product(specification);

        return productPersistencePort.save(product);
    }

    @Override
    public Optional<Product> getProductByCode(String code) {
        return productPersistencePort.getProductByCode(code);
    }

    @Override
    public PageResult<Product> findAll(PageQuery pageQuery) {
        return productPersistencePort.findAll(pageQuery);
    }

    @Override
    public Product updateSpecification(String code, ProductSpecification specification) {




        return null;
    }

}
