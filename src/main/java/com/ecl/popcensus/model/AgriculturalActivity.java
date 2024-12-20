// AgriculturalActivity.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "agricultural_activity")
public class AgriculturalActivity {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "crop_farming", nullable = false)
    private Boolean cropFarming;

    @Column(name = "tree_growing", nullable = false)
    private Boolean treeGrowing;

    @Column(name = "livestock", nullable = false)
    private Boolean livestock;

    @Column(name = "fish_farming", nullable = false)
    private Boolean fishFarming;

    @Column(name = "total_members_male")
    private Integer totalMembersMale;

    @Column(name = "total_members_female")
    private Integer totalMembersFemale;

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

    public Boolean getCropFarming() {
        return cropFarming;
    }

    public void setCropFarming(Boolean cropFarming) {
        this.cropFarming = cropFarming;
    }

    public Boolean getTreeGrowing() {
        return treeGrowing;
    }

    public void setTreeGrowing(Boolean treeGrowing) {
        this.treeGrowing = treeGrowing;
    }

    public Boolean getLivestock() {
        return livestock;
    }

    public void setLivestock(Boolean livestock) {
        this.livestock = livestock;
    }

    public Boolean getFishFarming() {
        return fishFarming;
    }

    public void setFishFarming(Boolean fishFarming) {
        this.fishFarming = fishFarming;
    }

    public Integer getTotalMembersMale() {
        return totalMembersMale;
    }

    public void setTotalMembersMale(Integer totalMembersMale) {
        this.totalMembersMale = totalMembersMale;
    }

    public Integer getTotalMembersFemale() {
        return totalMembersFemale;
    }

    public void setTotalMembersFemale(Integer totalMembersFemale) {
        this.totalMembersFemale = totalMembersFemale;
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






