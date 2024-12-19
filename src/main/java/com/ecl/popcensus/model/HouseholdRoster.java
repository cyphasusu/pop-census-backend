// HouseholdRoster.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "household_roster")
public class HouseholdRoster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "person_id", nullable = false)
    private Integer personId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "relationship_to_head")
    private String relationshipToHead;

    @Column(name = "relationship_code")
    private Integer relationshipCode;

    @Column(name = "sex")
    private String sex;

    @Column(name = "status", length = 1)
    private Character status;

    @Column(name = "household_id", unique = true)
    @NotNull
    private Long householdId;

    @Column(name = "date_of_birth")
    private LocalDateTime DOB;

    @Column(name = "age")
    private Integer age;

    @Column(name = "nationality")
    private Integer nationality;

    @Column(name = "ethnicity_group_name")
    private String ethnicityGroupName;

    @Column(name = "ethnicity_group_code")
    private Integer ethnicityGroupCode;

    @Column(name = "born_in_village_or_town")
    private Boolean bornInVillageOrTown;

    @Column(name = "birth_place")
    private Integer birthPlace;

    @Column(name = "living_in_village_or_town")
    private Boolean livingInVillageOrTown;

    @Column(name = "number_of_years_lived_in_town")
    private Integer numberOfYearsLivedInTown;

    @Column(name = "religion_code")
    private Integer religionCode;

    @Column(name = "marital_status_code")
    private Integer maritalStatusCode;

    @Column(name = "literacy_code")
    private Integer literacyCode;

    @Column(name = "attended_school")
    private String attendedSchool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "highest_level_of_schooling")
    private LevelOfSchool highestLevelOfSchooling;

    @Column(name = "highest_educational_grade")
    private Integer highestEducationalGrade;

    @Column(name = "census_form_id", unique = true)
    @NotNull
    private Long censusFormId;

    // Getters and Setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRelationshipToHead() {
        return relationshipToHead;
    }
    
    public void setRelationshipToHead(String relationshipToHead) {
        this.relationshipToHead = relationshipToHead;
    }
    
    public Integer getRelationshipCode() {
        return relationshipCode;
    }
    
    public void setRelationshipCode(Integer relationshipCode) {
        this.relationshipCode = relationshipCode;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public Character getStatus() {
        return status;
    }
    
    public void setStatus(Character status) {
        this.status = status;
    }
    
    public Long getHouseholdId() {
        return householdId;
    }
    
    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }
    
    public LocalDateTime getDOB() {
        return DOB;
    }
    
    public void setDOB(LocalDateTime DOB) {
        this.DOB = DOB;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public Integer getNationality() {
        return nationality;
    }
    
    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }
    
    public String getEthnicityGroupName() {
        return ethnicityGroupName;
    }
    
    public void setEthnicityGroupName(String ethnicityGroupName) {
        this.ethnicityGroupName = ethnicityGroupName;
    }
    
    public Integer getEthnicityGroupCode() {
        return ethnicityGroupCode;
    }
    
    public void setEthnicityGroupCode(Integer ethnicityGroupCode) {
        this.ethnicityGroupCode = ethnicityGroupCode;
    }
    
    public Boolean getBornInVillageOrTown() {
        return bornInVillageOrTown;
    }
    
    public void setBornInVillageOrTown(Boolean bornInVillageOrTown) {
        this.bornInVillageOrTown = bornInVillageOrTown;
    }
    
    public Integer getBirthPlace() {
        return birthPlace;
    }
    
    public void setBirthPlace(Integer birthPlace) {
        this.birthPlace = birthPlace;
    }
    
    public Boolean getLivingInVillageOrTown() {
        return livingInVillageOrTown;
    }
    
    public void setLivingInVillageOrTown(Boolean livingInVillageOrTown) {
        this.livingInVillageOrTown = livingInVillageOrTown;
    }
    
    public Integer getNumberOfYearsLivedInTown() {
        return numberOfYearsLivedInTown;
    }
    
    public void setNumberOfYearsLivedInTown(Integer numberOfYearsLivedInTown) {
        this.numberOfYearsLivedInTown = numberOfYearsLivedInTown;
    }
    
    public Integer getReligionCode() {
        return religionCode;
    }
    
    public void setReligionCode(Integer religionCode) {
        this.religionCode = religionCode;
    }
    
    public Integer getMaritalStatusCode() {
        return maritalStatusCode;
    }
    
    public void setMaritalStatusCode(Integer maritalStatusCode) {
        this.maritalStatusCode = maritalStatusCode;
    }
    
    public Integer getLiteracyCode() {
        return literacyCode;
    }
    
    public void setLiteracyCode(Integer literacyCode) {
        this.literacyCode = literacyCode;
    }
    
    public String getAttendedSchool() {
        return attendedSchool;
    }
    
    public void setAttendedSchool(String attendedSchool) {
        this.attendedSchool = attendedSchool;
    }
    
    public LevelOfSchool getHighestLevelOfSchooling() {
        return highestLevelOfSchooling;
    }
    
    public void setHighestLevelOfSchooling(LevelOfSchool highestLevelOfSchooling) {
        this.highestLevelOfSchooling = highestLevelOfSchooling;
    }
    
    public Integer getHighestEducationalGrade() {
        return highestEducationalGrade;
    }
    
    public void setHighestEducationalGrade(Integer highestEducationalGrade) {
        this.highestEducationalGrade = highestEducationalGrade;
    }
    
    public Long getCensusFormId() {
        return censusFormId;
    }
    
    public void setCensusFormId(Long censusFormId) {
        this.censusFormId = censusFormId;
    }

}
