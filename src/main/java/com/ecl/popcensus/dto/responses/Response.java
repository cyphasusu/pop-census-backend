package com.ecl.popcensus.dto.responses;

import lombok.Data;

@Data
public class Response extends  MessageResponse{
    public Boolean isCollection = false;
    public Integer count = 0;
    public Metadata metadata;
}
