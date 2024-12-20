// LivestockFisheries.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "livestock_fisheries")
public class LivestockFisheries {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "line_number")
    private Integer lineNumber;

    @Column(name = "type_of_livestock")
    private String typeOfLivestock;

    @Column(name = "livestock_fishery_code")
    private Integer livestockFisheryCode;

    @Column(name = "number")
    private Integer number;

    @Column(name = "household_id", nullable = false, unique = true)
    private Long householdId;

    @Column(name = "census_form_id", nullable = false, unique = true)
    private Long censusFormId;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getTypeOfLivestock() {
        return typeOfLivestock;
    }

    public void setTypeOfLivestock(String typeOfLivestock) {
        this.typeOfLivestock = typeOfLivestock;
    }

    public Integer getLivestockFisheryCode() {
        return livestockFisheryCode;
    }

    public void setLivestockFisheryCode(Integer livestockFisheryCode) {
        this.livestockFisheryCode = livestockFisheryCode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
