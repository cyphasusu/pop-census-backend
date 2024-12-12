package com.ecl.popcensus.dto.responses;

import lombok.Data;

@Data
public class Metadata {
    public long totalCount;
    public int totalPages;
    public boolean hasNextPage;
    public boolean hasPreviousPage;
    public int currentPage;
    public int pageSize;
}
