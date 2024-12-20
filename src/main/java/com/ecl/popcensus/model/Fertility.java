// Fertility.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "fertility")
public class Fertility {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "total_children_ever_born_male")
    private Integer totalChildrenEverBornMale;

    @Column(name = "total_children_ever_born_female")
    private Integer totalChildrenEverBornFemale;

    @Column(name = "total_children_surviving_male")
    private Integer totalChildrenSurvivingMale;

    @Column(name = "total_children_surviving_female")
    private Integer totalChildrenSurvivingFemale;

    @Column(name = "total_children_born_in_past_year_boy")
    private Integer totalChildrenBornInPastYearBoy;

    @Column(name = "total_children_born_in_past_year_girl")
    private Integer totalChildrenBornInPastYearGirl;

    @Column(name = "household_id", nullable = false)
    private Long householdId;

    @Column(name = "census_form_id", nullable = false)
    private Long censusFormId;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Integer getTotalChildrenEverBornMale() {
        return totalChildrenEverBornMale;
    }

    public void setTotalChildrenEverBornMale(Integer totalChildrenEverBornMale) {
        this.totalChildrenEverBornMale = totalChildrenEverBornMale;
    }

    public Integer getTotalChildrenEverBornFemale() {
        return totalChildrenEverBornFemale;
    }

    public void setTotalChildrenEverBornFemale(Integer totalChildrenEverBornFemale) {
        this.totalChildrenEverBornFemale = totalChildrenEverBornFemale;
    }

    public Integer getTotalChildrenSurvivingMale() {
        return totalChildrenSurvivingMale;
    }

    public void setTotalChildrenSurvivingMale(Integer totalChildrenSurvivingMale) {
        this.totalChildrenSurvivingMale = totalChildrenSurvivingMale;
    }

    public Integer getTotalChildrenSurvivingFemale() {
        return totalChildrenSurvivingFemale;
    }

    public void setTotalChildrenSurvivingFemale(Integer totalChildrenSurvivingFemale) {
        this.totalChildrenSurvivingFemale = totalChildrenSurvivingFemale;
    }

    public Integer getTotalChildrenBornInPastYearBoy() {
        return totalChildrenBornInPastYearBoy;
    }

    public void setTotalChildrenBornInPastYearBoy(Integer totalChildrenBornInPastYearBoy) {
        this.totalChildrenBornInPastYearBoy = totalChildrenBornInPastYearBoy;
    }

    public Integer getTotalChildrenBornInPastYearGirl() {
        return totalChildrenBornInPastYearGirl;
    }

    public void setTotalChildrenBornInPastYearGirl(Integer totalChildrenBornInPastYearGirl) {
        this.totalChildrenBornInPastYearGirl = totalChildrenBornInPastYearGirl;
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






