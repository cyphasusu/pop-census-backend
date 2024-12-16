package com.ecl.popcensus.dto.requests;

import javax.validation.constraints.NotNull;

public class CensusFormRequest {
    @NotNull
    private String censusFormName;

    @NotNull
    private String censusFormLocation;

    private String censusFormDesc;



    public String getCensusFormName() {
        return censusFormName;
    }

    public void setCensusFormName(String censusFormName) {
        this.censusFormName = censusFormName;
    }

    public String getCensusFormLocation() {
        return censusFormLocation;
    }

    public void setCensusFormLocation(String censusFormLocation) {
        this.censusFormLocation = censusFormLocation;
    }

    public String getCensusFormDesc() {
        return censusFormDesc;
    }

    public void setCensusFormDesc(String censusFormDesc) {
        this.censusFormDesc = censusFormDesc;
    }
}
