package com.example.application.model;

import com.example.application.enumeration.PaperReviewStateEnum;
import com.example.application.enumeration.ReviewerDecisionEnum;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String filename;

    @ManyToMany(cascade = CascadeType.PERSIST) // Give the currently set entity the authority to operate another entity.
    private Set<Keyword> keywords;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    private PaperReviewStateEnum state;

    private String comments1;

    private ReviewerDecisionEnum decision1;

    private String comments2;

    private ReviewerDecisionEnum decision2;

    private String comments3;

    private ReviewerDecisionEnum decision3;
}
