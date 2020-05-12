package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Submission {
    @EmbeddedId
    private SubmissionId submissionId;

    @NotNull
    @NotBlank
    private Float score;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDate createdAt;
}
