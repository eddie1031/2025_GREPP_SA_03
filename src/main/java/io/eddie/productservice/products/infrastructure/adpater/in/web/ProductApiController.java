package io.eddie.productservice.products.infrastructure.adpater.in.web;

import io.eddie.productservice.products.application.port.in.ProductCrudUseCase;
import io.eddie.productservice.products.domain.exception.CouldNotFindProductException;
import io.eddie.productservice.products.domain.vo.ProductSpecification;
import io.eddie.productservice.products.domain.vo.pagination.PageQuery;
import io.eddie.productservice.products.domain.vo.pagination.PageResult;
import io.eddie.productservice.products.domain.vo.pagination.SortSpec;
import io.eddie.productservice.products.infrastructure.mapper.ProductMapper;
import io.eddie.productservice.products.infrastructure.model.dto.ProductDescription;
import io.eddie.productservice.products.infrastructure.model.web.BaseResponse;
import io.eddie.productservice.products.infrastructure.model.web.ProductSpecificationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductApiController {

    private final ProductCrudUseCase productCrudUseCase;

    @PostMapping
    public BaseResponse<ProductDescription> createProduct(
            @RequestBody @Valid ProductSpecificationRequest request
    ) {

        ProductSpecification spec = ProductMapper.toSpecification(request);

        return new BaseResponse<>(
                ProductMapper.toDescription(
                        productCrudUseCase.enroll(
                                spec
                        )
                ),
                "상품이 성공적으로 생성되었습니다."
        );

    }

    @GetMapping("/{code}")
    public BaseResponse<ProductDescription> getProductByCode(
            @PathVariable String code
    ) {
        return new BaseResponse<>(
                ProductMapper.toDescription(
                        productCrudUseCase.getProductByCode(code)
                                .orElseThrow(CouldNotFindProductException::new)
                ),
                "상품을 성공적으로 조회하였습니다."
        );

    }

    @GetMapping
    public BaseResponse<PageResult<ProductDescription>> getProductPage(
            Pageable pageable
    ) {

        return new BaseResponse<>(
                ProductMapper.toDescriptionResult(
                        productCrudUseCase.findAll(new PageQuery(
                                pageable.getPageNumber(),
                                pageable.getPageSize(),
                                new SortSpec("createdAt", SortSpec.Direction.ASC)
                        ))
                ),
                "목록을 성공적으로 조회하였습니다."
        );
    }

    @PatchMapping("/{code}")
    public BaseResponse<ProductDescription> updateProduct(
            @PathVariable String code,
            @RequestBody @Valid ProductSpecificationRequest request
    ) {
        return new BaseResponse<>(
                ProductMapper.toDescription(
                        productCrudUseCase.updateSpecification(code, ProductMapper.toSpecification(request))
                ),
                "상품을 성공적으로 수정하였습니다."
        );
    }

}
