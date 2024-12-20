// EconomicActivity.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "economic_activity")
public class EconomicActivity {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "person_id")
    @NotNull
    private Integer personId;

    @Column(name = "helping_family_business")
    private Boolean helpingFamilyBusiness;

    @Column(name = "how_person_mainly_engaged")
    private String howPersonMainlyEngaged;

    @Column(name = "why_person_not_seek_work")
    private String whyPersonNotSeekWork;

    @Column(name = "occupation_description")
    private String occupationDescription;

    @Column(name = "occupation_code")
    private Integer occupationCode;

    @Column(name = "name_and_physical_location")
    private String nameAndPhysicalLocation;

    @Column(name = "industry_main_products_or_service")
    private String industryMainProductsOrService;

    @Column(name = "industry_main_products_or_service_code")
    private Integer industryMainProductsOrServiceCode;

    @Column(name = "employment_status")
    private Integer employmentStatus;

    @Column(name = "employment_sector")
    private Integer employmentSector;

    @Column(name = "disability_sight")
    private Boolean disabilitySight;

    @Column(name = "disability_hearing")
    private Boolean disabilityHearing;

    @Column(name = "disability_speech")
    private Boolean disabilitySpeech;

    @Column(name = "disability_physical")
    private Boolean disabilityPhysical;

    @Column(name = "disability_intellect")
    private Boolean disabilityIntellect;

    @Column(name = "disability_emotional")
    private Boolean disabilityEmotional;

    @Column(name = "disability_other")
    private Boolean disabilityOther;

    @Column(name = "use_mobile_phone")
    private Boolean useMobilePhone;

    @Column(name = "use_mobile_facility")
    private Boolean useMobileFacility;

    @Column(name = "use_internet_facility")
    private Boolean useInternetFacility;

    @Column(name = "household_id")
    @NotNull
    private Long householdId;

    @Column(name = "census_form_id")
    @NotNull
    private Long censusFormId;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Boolean getHelpingFamilyBusiness() {
        return helpingFamilyBusiness;
     }
     
     public void setHelpingFamilyBusiness(Boolean helpingFamilyBusiness) {
        this.helpingFamilyBusiness = helpingFamilyBusiness;
     }
     
     public String getHowPersonMainlyEngaged() {
        return howPersonMainlyEngaged;
     }
     
     public void setHowPersonMainlyEngaged(String howPersonMainlyEngaged) {
        this.howPersonMainlyEngaged = howPersonMainlyEngaged;
     }
     
     public String getWhyPersonNotSeekWork() {
        return whyPersonNotSeekWork;
     }
     
     public void setWhyPersonNotSeekWork(String whyPersonNotSeekWork) {
        this.whyPersonNotSeekWork = whyPersonNotSeekWork;
     }
     
     public String getOccupationDescription() {
        return occupationDescription;
     }
     
     public void setOccupationDescription(String occupationDescription) {
        this.occupationDescription = occupationDescription;
     }
     
     public Integer getOccupationCode() {
        return occupationCode;
     }
     
     public void setOccupationCode(Integer occupationCode) {
        this.occupationCode = occupationCode;
     }
     
     public String getNameAndPhysicalLocation() {
        return nameAndPhysicalLocation;
     }
     
     public void setNameAndPhysicalLocation(String nameAndPhysicalLocation) {
        this.nameAndPhysicalLocation = nameAndPhysicalLocation;
     }
     
     public String getIndustryMainProductsOrService() {
        return industryMainProductsOrService;
     }
     
     public void setIndustryMainProductsOrService(String industryMainProductsOrService) {
        this.industryMainProductsOrService = industryMainProductsOrService;
     }
     
     public Integer getIndustryMainProductsOrServiceCode() {
        return industryMainProductsOrServiceCode;
     }
     
     public void setIndustryMainProductsOrServiceCode(Integer industryMainProductsOrServiceCode) {
        this.industryMainProductsOrServiceCode = industryMainProductsOrServiceCode;
     }
     
     public Integer getEmploymentStatus() {
        return employmentStatus;
     }
     
     public void setEmploymentStatus(Integer employmentStatus) {
        this.employmentStatus = employmentStatus;
     }
     
     public Integer getEmploymentSector() {
        return employmentSector;
     }
     
     public void setEmploymentSector(Integer employmentSector) {
        this.employmentSector = employmentSector;
     }
     
     public Boolean getDisabilitySight() {
        return disabilitySight;
     }
     
     public void setDisabilitySight(Boolean disabilitySight) {
        this.disabilitySight = disabilitySight;
     }
     
     public Boolean getDisabilityHearing() {
        return disabilityHearing;
     }
     
     public void setDisabilityHearing(Boolean disabilityHearing) {
        this.disabilityHearing = disabilityHearing;
     }
     
     public Boolean getDisabilitySpeech() {
        return disabilitySpeech;
     }
     
     public void setDisabilitySpeech(Boolean disabilitySpeech) {
        this.disabilitySpeech = disabilitySpeech;
     }
     
     public Boolean getDisabilityPhysical() {
        return disabilityPhysical;
     }
     
     public void setDisabilityPhysical(Boolean disabilityPhysical) {
        this.disabilityPhysical = disabilityPhysical;
     }
     
     public Boolean getDisabilityIntellect() {
        return disabilityIntellect;
     }
     
     public void setDisabilityIntellect(Boolean disabilityIntellect) {
        this.disabilityIntellect = disabilityIntellect;
     }
     
     public Boolean getDisabilityEmotional() {
        return disabilityEmotional;
     }
     
     public void setDisabilityEmotional(Boolean disabilityEmotional) {
        this.disabilityEmotional = disabilityEmotional;
     }
     
     public Boolean getDisabilityOther() {
        return disabilityOther;
     }
     
     public void setDisabilityOther(Boolean disabilityOther) {
        this.disabilityOther = disabilityOther;
     }
     
     public Boolean getUseMobilePhone() {
        return useMobilePhone;
     }
     
     public void setUseMobilePhone(Boolean useMobilePhone) {
        this.useMobilePhone = useMobilePhone;
     }
     
     public Boolean getUseMobileFacility() {
        return useMobileFacility;
     }
     
     public void setUseMobileFacility(Boolean useMobileFacility) {
        this.useMobileFacility = useMobileFacility;
     }
     
     public Boolean getUseInternetFacility() {
        return useInternetFacility;
     }
     
     public void setUseInternetFacility(Boolean useInternetFacility) {
        this.useInternetFacility = useInternetFacility;
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
