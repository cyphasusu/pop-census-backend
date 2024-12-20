// LevelOfSchool.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "level_of_school")
public class LevelOfSchool {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "school_level_code", unique = true)
    @NotNull
    private String schoolLevelCode;

    @Column(name = "school_level_desc")
    private String schoolLevelDesc;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolLevelCode() {
        return schoolLevelCode;
    }

    public void setSchoolLevelCode(String schoolLevelCode) {
        this.schoolLevelCode = schoolLevelCode;
    }

    public String getSchoolLevelDesc() {
        return schoolLevelDesc;
    }

    public void setSchoolLevelDesc(String schoolLevelDesc) {
        this.schoolLevelDesc = schoolLevelDesc;
    }
}
