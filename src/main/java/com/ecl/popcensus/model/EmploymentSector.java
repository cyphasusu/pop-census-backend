// EmploymentSector.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "employment_sector")
public class EmploymentSector {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "employment_sector_code", unique = true)
    @NotNull
    private String employmentSectorCode;

    @Column(name = "employment_sector_desc")
    private String employmentSectorDesc;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmploymentSectorCode() {
        return employmentSectorCode;
    }

    public void setEmploymentSectorCode(String employmentSectorCode) {
        this.employmentSectorCode = employmentSectorCode;
    }

    public String getEmploymentSectorDesc() {
        return employmentSectorDesc;
    }

    public void setEmploymentSectorDesc(String employmentSectorDesc) {
        this.employmentSectorDesc = employmentSectorDesc;
    }
}
