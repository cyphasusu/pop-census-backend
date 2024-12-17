package com.ecl.popcensus.dto.mapping;

import com.ecl.popcensus.model.CensusForm;
import com.ecl.popcensus.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class MapCensusForm {
    public static CensusForm mapToCensusFormEntity(CensusForm censusForm)
    {
        if(censusForm == null){
            return null;
        }

        var dto = new CensusForm();
        dto.setCensusFormId(censusForm.getCensusFormId());
        dto.setCensusFormName(censusForm.getCensusFormName());
        dto.setCensusFormLocation(censusForm.getCensusFormLocation());
        dto.setCensusFormDesc(censusForm.getCensusFormDesc());
        dto.setCensusFormStatus(censusForm.getCensusFormStatus());
        dto.setCreatedAt(censusForm.getCreatedAt());
        dto.setCreatedBy(censusForm.getCreatedBy());
        dto.setModifiedAt(censusForm.getModifiedAt());
        dto.setModifiedBy(censusForm.getModifiedBy());
        dto.setDeletedAt(censusForm.getDeletedAt());
        dto.setDeletedBy(censusForm.getDeletedBy());

        return dto;

    }

    public static List<CensusForm> mapToCensusFormList(List<CensusForm> censusForms)
    {
        return censusForms.stream()
                .map(MapCensusForm::mapToCensusFormEntity)
                .collect(Collectors.toList());
    }
}
