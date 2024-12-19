// Interview.java
package com.ecl.popcensus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_started")
    @NotNull
    private LocalDateTime dateStarted;

    @Column(name = "date_completed")
    private LocalDateTime dateCompleted;

    @Column(name = "total_number_of_visits")
    private Integer totalNumberOfVisits;

    @Column(name = "household_id", unique = true)
    @NotNull
    private Long householdId;

    @Column(name = "census_form_id", unique = true)
    @NotNull
    private Long censusFormId;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
    }

    public LocalDateTime getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDateTime dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public Integer getTotalNumberOfVisits() {
        return totalNumberOfVisits;
    }

    public void setTotalNumberOfVisits(Integer totalNumberOfVisits) {
        this.totalNumberOfVisits = totalNumberOfVisits;
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
