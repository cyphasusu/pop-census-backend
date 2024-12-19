package com.ecl.popcensus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "emigration")
public class Emigration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "former_members_living_outside")
    private Boolean formerMembersLivingOutside;

    @Column(name = "line_number")
    private Integer lineNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "relationship_to_head")
    private String relationshipToHead;

    @Column(name = "code")
    private Integer code;

    @Column(name = "sex")
    private String sex;

    @Column(name = "age")
    private Integer age;

    @Column(name = "destination_country_name")
    private String destinationCountryName;

    @Column(name = "destination_country_code")
    private Integer destinationCountryCode;

    @Column(name = "year_of_departure")
    private String yearOfDeparture;

    @Column(name = "activity_abroad_code")
    private Integer activityAbroadCode;

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

    public Boolean getFormerMembersLivingOutside() {
        return formerMembersLivingOutside;
    }

    public void setFormerMembersLivingOutside(Boolean formerMembersLivingOutside) {
        this.formerMembersLivingOutside = formerMembersLivingOutside;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDestinationCountryName() {
        return destinationCountryName;
    }

    public void setDestinationCountryName(String destinationCountryName) {
        this.destinationCountryName = destinationCountryName;
    }

    public Integer getDestinationCountryCode() {
        return destinationCountryCode;
    }

    public void setDestinationCountryCode(Integer destinationCountryCode) {
        this.destinationCountryCode = destinationCountryCode;
    }

    public String getYearOfDeparture() {
        return yearOfDeparture;
    }

    public void setYearOfDeparture(String yearOfDeparture) {
        this.yearOfDeparture = yearOfDeparture;
    }

    public Integer getActivityAbroadCode() {
        return activityAbroadCode;
    }

    public void setActivityAbroadCode(Integer activityAbroadCode) {
        this.activityAbroadCode = activityAbroadCode;
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






