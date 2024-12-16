package com.ecl.popcensus.dto.responses;

import com.ecl.popcensus.model.CensusForm;
import com.ecl.popcensus.model.User;
import lombok.Data;

import java.util.List;

@Data
public class CensusListResponse extends Response{
    private List<CensusForm> data;
}
