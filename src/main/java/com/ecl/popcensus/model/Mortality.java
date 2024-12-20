// Mortality.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "mortality")
public class Mortality {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "member_died_in_past_year", nullable = false)
    private Boolean memberDiedInPastYear;

    @Column(name = "line_number")
    private Integer lineNumber;

    @Column(name = "name_of_deceased")
    private String nameOfDeceased;

    @Column(name = "sex_of_deceased")
    private String sexOfDeceased;

    @Column(name = "age_at_death")
    private Integer ageAtDeath;

    @Column(name = "death_due_to_accident_violence_homicide_suicide")
    private Boolean deathDueToAccidentViolenceHomicideSuicide;

    @Column(name = "death_while_pregnant")
    private Boolean deathWhilePregnant;

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

    public Boolean getMemberDiedInPastYear() {
        return memberDiedInPastYear;
    }

    public void setMemberDiedInPastYear(Boolean memberDiedInPastYear) {
        this.memberDiedInPastYear = memberDiedInPastYear;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getNameOfDeceased() {
        return nameOfDeceased;
    }

    public void setNameOfDeceased(String nameOfDeceased) {
        this.nameOfDeceased = nameOfDeceased;
    }

    public String getSexOfDeceased() {
        return sexOfDeceased;
    }

    public void setSexOfDeceased(String sexOfDeceased) {
        this.sexOfDeceased = sexOfDeceased;
    }

    public Integer getAgeAtDeath() {
        return ageAtDeath;
    }

    public void setAgeAtDeath(Integer ageAtDeath) {
        this.ageAtDeath = ageAtDeath;
    }

    public Boolean getDeathDueToAccidentViolenceHomicideSuicide() {
        return deathDueToAccidentViolenceHomicideSuicide;
    }

    public void setDeathDueToAccidentViolenceHomicideSuicide(Boolean deathDueToAccidentViolenceHomicideSuicide) {
        this.deathDueToAccidentViolenceHomicideSuicide = deathDueToAccidentViolenceHomicideSuicide;
    }

    public Boolean getDeathWhilePregnant() {
        return deathWhilePregnant;
    }

    public void setDeathWhilePregnant(Boolean deathWhilePregnant) {
        this.deathWhilePregnant = deathWhilePregnant;
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






