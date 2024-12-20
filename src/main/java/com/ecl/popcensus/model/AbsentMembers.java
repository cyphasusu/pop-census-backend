// AbsentMembers.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "absent_members")
public class AbsentMembers {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "number_of_absentees")
    private Integer numberOfAbsentees;

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

    @Column(name = "destination")
    private String destination;

    @Column(name = "region_country_code")
    private Integer regionCountryCode;

    @Column(name = "months_absent")
    private Integer monthsAbsent;

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

    public Integer getNumberOfAbsentees() {
        return numberOfAbsentees;
    }

    public void setNumberOfAbsentees(Integer numberOfAbsentees) {
        this.numberOfAbsentees = numberOfAbsentees;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getRegionCountryCode() {
        return regionCountryCode;
    }

    public void setRegionCountryCode(Integer regionCountryCode) {
        this.regionCountryCode = regionCountryCode;
    }

    public Integer getMonthsAbsent() {
        return monthsAbsent;
    }

    public void setMonthsAbsent(Integer monthsAbsent) {
        this.monthsAbsent = monthsAbsent;
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






