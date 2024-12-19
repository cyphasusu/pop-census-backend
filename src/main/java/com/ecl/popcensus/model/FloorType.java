// FloorType.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "floor_type")
public class FloorType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "floor_type_code", unique = true, nullable = false)
    private String floorTypeCode;

    @NotBlank
    @Column(name = "floor_type_desc", nullable = false)
    private String floorTypeDesc;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFloorTypeCode() {
        return floorTypeCode;
    }

    public void setFloorTypeCode(String floorTypeCode) {
        this.floorTypeCode = floorTypeCode;
    }

    public String getFloorTypeDesc() {
        return floorTypeDesc;
    }

    public void setFloorTypeDesc(String floorTypeDesc) {
        this.floorTypeDesc = floorTypeDesc;
    }
}
