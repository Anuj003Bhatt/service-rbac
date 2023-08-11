package com.decimal.rbac.model.dtos;

import com.decimal.rbac.model.rest.response.ListResponse;
import org.springframework.data.domain.Page;

public abstract class BridgeUtil {

    public static <T,I extends DtoBridge<T>> ListResponse<T> buildPaginatedResponse (Page<I> list) {
        return ListResponse.<T>builder()
                .data(list.map(I::toDto).toList())
                .pagination(
                        Pagination
                                .builder()
                                .totalPages(list.getTotalPages())
                                .currentPage(list.getNumber())
                                .elements(list.getTotalElements())
                                .build()
                ).build();
    }
}
