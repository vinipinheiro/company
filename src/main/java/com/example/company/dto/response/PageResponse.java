package com.example.company.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<T> {

    @ApiModelProperty(example="1", value="Current Page")
    private int currentPage;
    @ApiModelProperty(example="10", value="Total Records per Page")
    private int pageTotalRecords;
    @ApiModelProperty(example="3", value="Total of Pages")
    private int totalPages;
    @ApiModelProperty(example="25", value="Total of Records")
    private int totalRecords;
    @ApiModelProperty(example="1", value="Content of Page")
    private List<T> content;
}
