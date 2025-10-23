package io.eddie.productservice.products.domain.vo.pagination;

import java.util.List;

public record PageResult<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        boolean hasNext
) {
}
