package com.ecl.popcensus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "geographical_information")
public class GeographicalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "enumeration_area_code")
    private Integer enumerationAreaCode;

    @Column(name = "ea_type")
    private String EAType;

    @Column(name = "locality_code")
    private Integer localityCode;

    @Column(name = "structure_number")
    private Integer structureNumber;

    @Column(name = "household_number")
    private Integer householdNumber;

    @Column(name = "type_of_residence")
    private Integer typeOfResidence;

    @Column(name = "household_id", unique = true)
    @NotNull
    private Long householdId;

    @Column(name = "census_form_id", unique = true)
    @NotNull
    private Long censusFormId;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEnumerationAreaCode() {
        return enumerationAreaCode;
    }

    public void setEnumerationAreaCode(Integer enumerationAreaCode) {
        this.enumerationAreaCode = enumerationAreaCode;
    }

    public String getEAType() {
        return EAType;
    }

    public void setEAType(String EAType) {
        this.EAType = EAType;
    }

    public Integer getLocalityCode() {
        return localityCode;
    }

    public void setLocalityCode(Integer localityCode) {
        this.localityCode = localityCode;
    }

    public Integer getStructureNumber() {
        return structureNumber;
    }

    public void setStructureNumber(Integer structureNumber) {
        this.structureNumber = structureNumber;
    }

    public Integer getHouseholdNumber() {
        return householdNumber;
    }

    public void setHouseholdNumber(Integer householdNumber) {
        this.householdNumber = householdNumber;
    }

    public Integer getTypeOfResidence() {
        return typeOfResidence;
    }

    public void setTypeOfResidence(Integer typeOfResidence) {
        this.typeOfResidence = typeOfResidence;
    }

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }

    public Long getCensusFormId() {
        return censusFormId;
    }

    public void setCensusFormId(Long censusFormId) {
        this.censusFormId = censusFormId;
    }
}






