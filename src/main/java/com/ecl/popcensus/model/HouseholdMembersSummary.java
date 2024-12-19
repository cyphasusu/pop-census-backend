// HouseholdMembersSummary.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "household_members_summary")
public class HouseholdMembersSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status_a_male")
    private Integer statusAMale;

    @Column(name = "status_a_female")
    private Integer statusAFemale;

    @Column(name = "status_b_male")
    private Integer statusBMale;

    @Column(name = "status_b_female")
    private Integer statusBFemale;

    @Column(name = "status_c_male")
    private Integer statusCMale;

    @Column(name = "status_c_female")
    private Integer statusCFemale;

    @Column(name = "status_a_b_male")
    private Integer statusABMale;

    @Column(name = "status_a_b_female")
    private Integer statusABFemale;

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

    public Integer getStatusAMale() {
        return statusAMale;
    }

    public void setStatusAMale(Integer statusAMale) {
        this.statusAMale = statusAMale;
    }

    public Integer getStatusAFemale() {
        return statusAFemale;
    }

    public void setStatusAFemale(Integer statusAFemale) {
        this.statusAFemale = statusAFemale;
    }

    public Integer getStatusBMale() {
        return statusBMale;
    }

    public void setStatusBMale(Integer statusBMale) {
        this.statusBMale = statusBMale;
    }

    public Integer getStatusBFemale() {
        return statusBFemale;
    }

    public void setStatusBFemale(Integer statusBFemale) {
        this.statusBFemale = statusBFemale;
    }

    public Integer getStatusCMale() {
        return statusCMale;
    }

    public void setStatusCMale(Integer statusCMale) {
        this.statusCMale = statusCMale;
    }

    public Integer getStatusCFemale() {
        return statusCFemale;
    }

    public void setStatusCFemale(Integer statusCFemale) {
        this.statusCFemale = statusCFemale;
    }

    public Integer getStatusABMale() {
        return statusABMale;
    }

    public void setStatusABMale(Integer statusABMale) {
        this.statusABMale = statusABMale;
    }

    public Integer getStatusABFemale() {
        return statusABFemale;
    }

    public void setStatusABFemale(Integer statusABFemale) {
        this.statusABFemale = statusABFemale;
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

