// EmploymentStatus.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "employment_status")
public class EmploymentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "employment_status_code", unique = true)
    @NotNull
    private String employmentStatusCode;

    @Column(name = "employment_status_desc")
    private String employmentStatusDesc;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmploymentStatusCode() {
        return employmentStatusCode;
    }

    public void setEmploymentStatusCode(String employmentStatusCode) {
        this.employmentStatusCode = employmentStatusCode;
    }

    public String getEmploymentStatusDesc() {
        return employmentStatusDesc;
    }

    public void setEmploymentStatusDesc(String employmentStatusDesc) {
        this.employmentStatusDesc = employmentStatusDesc;
    }
}
