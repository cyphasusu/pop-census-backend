package com.ecl.popcensus.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.ToString;
import java.util.Date;


@Entity
@Table(name = "household_information")
public class HouseholdInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", nullable = false)
    private CensusForm censusForm;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "district_type")
    private String districtType;

    @Column(name = "sub_district")
    private String subDistrict;

    @Column(name = "locality_name")
    private String localityName;

    @Column(name = "verification_id")
    private String verificationId;

    @Column(name = "detailed_address")
    private String detailedAddress;

    @Column(name = "contact_phone_number1")
    private String contactPhoneNumber1;

    @Column(name = "contact_phone_number2")
    private String contactPhoneNumber2;

    // Getters and setters...

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for censusForm
    public CensusForm getCensusForm() {
        return censusForm;
    }

    public void setCensusForm(CensusForm censusForm) {
        this.censusForm = censusForm;
    }

    // Getter and Setter for regionName
    public String getRegionName() {
        return regionName;
    }


    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }


    // Getter and Setter for districtName
    public String getDistrictName() {
        return districtName;
    }


    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    // Getter and Setter for districtType
    public String getDistrictType() {
        return districtType;
    }

    public void setDistrictType(String districtType) {
        this.districtType = districtType;
    }

    // Getter and Setter for subDistrict
    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    // Getter and Setter for localityName
    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }
    
    // Getter and Setter for verificationId
    public String getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }

    // Getter and Setter for detailedAddress
    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    // Getter and Setter for contactPhoneNumber1
    public String getContactPhoneNumber1() {
        return contactPhoneNumber1;
    }

    public void setContactPhoneNumber1(String contactPhoneNumber1) {
        this.contactPhoneNumber1 = contactPhoneNumber1;
    }

    // Getter and Setter for contactPhoneNumber2
    public String getContactPhoneNumber2() {
        return contactPhoneNumber2;
    }

    public void setContactPhoneNumber2(String contactPhoneNumber2) {
        this.contactPhoneNumber2 = contactPhoneNumber2;
    }

    
}
