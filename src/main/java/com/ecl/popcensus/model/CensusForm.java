package com.ecl.popcensus.model;

import com.ecl.popcensus.util.validators.NameConstraint;
import com.ecl.popcensus.util.validators.OnCreate;
import com.ecl.popcensus.util.validators.PhoneNumberConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class CensusForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "census_form_id")
    private Long censusFormId;

    @NameConstraint
    @Column(name = "census_form_name", nullable = false)
    private String censusFormName;

    @NameConstraint
    @Column(name = "census_form_location", nullable = false)
    private String censusFormLocation;

    @Column(name = "census_form_desc")
    private String censusFormDesc;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "census_form_status")
    private UserStatus censusFormStatus;


    public String getCensusFormLocation() {
        return censusFormLocation;
    }

    public void setCensusFormLocation(String censusFormLocation) {
        this.censusFormLocation = censusFormLocation;
    }

    public Long getCensusFormId() {
        return censusFormId;
    }

    public void setCensusFormId(Long censusFormId) {
        this.censusFormId = censusFormId;
    }

    public String getCensusFormName() {
        return censusFormName;
    }

    public void setCensusFormName(String censusFormName) {
        this.censusFormName = censusFormName;
    }

    public String getCensusFormDesc() {
        return censusFormDesc;
    }

    public void setCensusFormDesc(String censusFormDesc) {
        this.censusFormDesc = censusFormDesc;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public UserStatus getCensusFormStatus() {
        return censusFormStatus;
    }

    public void setCensusFormStatus(UserStatus censusFormStatus) {
        this.censusFormStatus = censusFormStatus;
    }


}