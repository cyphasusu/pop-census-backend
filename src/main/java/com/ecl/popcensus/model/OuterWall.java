// OuterWall.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "outer_wall")
public class OuterWall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "outer_wall_code", unique = true, nullable = false)
    private String outerWallCode;

    @NotBlank
    @Column(name = "outer_wall_desc", nullable = false)
    private String outerWallDesc;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOuterWallCode() {
        return outerWallCode;
    }

    public void setOuterWallCode(String outerWallCode) {
        this.outerWallCode = outerWallCode;
    }

    public String getOuterWallDesc() {
        return outerWallDesc;
    }

    public void setOuterWallDesc(String outerWallDesc) {
        this.outerWallDesc = outerWallDesc;
    }
}
