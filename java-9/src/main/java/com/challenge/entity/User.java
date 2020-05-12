package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String nickname;

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String password;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDate createdAt;

    @OneToMany
    private List<Submission> submissions;

    @OneToMany
    private List<Candidate> candidates;
}
