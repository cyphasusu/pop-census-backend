// HouseholdInformationRequest.java
package com.ecl.popcensus.dto.requests;

import javax.validation.constraints.NotNull;

public class HouseholdInformationRequest {
    private String regionName;
    private String districtName;
    private String districtType;
    private String subDistrict;
    private String localityName;
    private String verificationId;
    private String detailedAddress;
    private String contactPhoneNumber1;
    private String contactPhoneNumber2;

    // Getters and setters
    // ... (include all getters and setters for the above fields)
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

