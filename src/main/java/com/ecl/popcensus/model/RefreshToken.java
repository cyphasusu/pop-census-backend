package com.ecl.popcensus.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "refresh_tokens")
@NoArgsConstructor
@AllArgsConstructor

public class RefreshToken {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @Column(unique = true)
    private String token;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private Long refreshCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssZ")
    private Date expiryDate=new Date();

    public void incrementRefreshCount() {
        refreshCount = refreshCount + 1;
    }
}
