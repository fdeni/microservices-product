package com.project.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginationBaseResponse<T> {
    @JsonProperty(value = "currentPage")
    private Integer currentPage;

    @JsonProperty(value = "totalPage")
    private Integer totalPage;

    @JsonProperty(value = "totalData")
    private long totalData;

    @JsonProperty(value = "pagingData")
    private List<T> pagingData;
}
