package com.ecl.popcensus.dto.responses;


import com.ecl.popcensus.model.CensusForm;
import com.ecl.popcensus.model.User;
import lombok.Data;


@Data
public class CensusFormResponse extends Response {
    private CensusForm data;
}


