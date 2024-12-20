// DwellingType.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "dwelling_type")
public class DwellingType {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "dwelling_type_code", unique = true, nullable = false)
    private String dwellingTypeCode;

    @NotBlank
    @Column(name = "dwelling_type_desc", nullable = false)
    private String dwellingTypeDesc;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDwellingTypeCode() {
        return dwellingTypeCode;
    }

    public void setDwellingTypeCode(String dwellingTypeCode) {
        this.dwellingTypeCode = dwellingTypeCode;
    }

    public String getDwellingTypeDesc() {
        return dwellingTypeDesc;
    }

    public void setDwellingTypeDesc(String dwellingTypeDesc) {
        this.dwellingTypeDesc = dwellingTypeDesc;
    }
}









