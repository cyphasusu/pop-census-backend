package com.ecl.popcensus.dto.responses;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

@Data
public class MessageResponse {
    private String responseCode;
    private String responseMessage;
}
