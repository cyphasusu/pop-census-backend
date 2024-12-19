// CropTreeFarming.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "crop_tree_farming")
public class CropTreeFarming {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "line_number")
    private Integer lineNumber;

    @Column(name = "type_of_crop_and_tree_planting")
    private String typeOfCropAndTreePlanting;

    @Column(name = "crop_code")
    private Integer cropCode;

    @Column(name = "farm_size")
    private Integer farmSize;

    @Column(name = "measurement_unit")
    @Enumerated(EnumType.STRING)
    private MeasurementUnit measurementUnit;

    @Column(name = "type_of_cropping")
    @Enumerated(EnumType.STRING)
    private CroppingType typeOfCropping;

    @Column(name = "household_id", nullable = false, unique = true)
    private Long householdId;

    @Column(name = "census_form_id", nullable = false, unique = true)
    private Long censusFormId;

    // Enums for measurement unit and cropping type
    public enum MeasurementUnit {
        ACRE("Acre"),
        HECTARE("Hectare"),
        POLE("Pole"),
        ROPE("Rope"),
        PLOT("Plot");

        private final String value;

        MeasurementUnit(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum CroppingType {
        MIXED_CROPPING("Mixed Cropping"),
        INTER_CROPPING("Inter Cropping"),
        MONO_CROPPING("Mono Cropping");

        private final String value;

        CroppingType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getTypeOfCropAndTreePlanting() {
        return typeOfCropAndTreePlanting;
    }

    public void setTypeOfCropAndTreePlanting(String typeOfCropAndTreePlanting) {
        this.typeOfCropAndTreePlanting = typeOfCropAndTreePlanting;
    }

    public Integer getCropCode() {
        return cropCode;
    }

    public void setCropCode(Integer cropCode) {
        this.cropCode = cropCode;
    }

    public Integer getFarmSize() {
        return farmSize;
    }

    public void setFarmSize(Integer farmSize) {
        this.farmSize = farmSize;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public CroppingType getTypeOfCropping() {
        return typeOfCropping;
    }

    public void setTypeOfCropping(CroppingType typeOfCropping) {
        this.typeOfCropping = typeOfCropping;
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





