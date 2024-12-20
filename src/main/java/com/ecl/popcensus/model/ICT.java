// ICT.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "ict")
public class ICT {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "home_telephone_line", nullable = false)
    private Boolean homeTelephoneLine;

    @Column(name = "house_member_own_computer", nullable = false)
    private Boolean houseMemberOwnComputer;

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

    public Boolean getHomeTelephoneLine() {
        return homeTelephoneLine;
    }

    public void setHomeTelephoneLine(Boolean homeTelephoneLine) {
        this.homeTelephoneLine = homeTelephoneLine;
    }

    public Boolean getHouseMemberOwnComputer() {
        return houseMemberOwnComputer;
    }

    public void setHouseMemberOwnComputer(Boolean houseMemberOwnComputer) {
        this.houseMemberOwnComputer = houseMemberOwnComputer;
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






