package com.example.application.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String keyword;

    @ManyToMany(mappedBy = "keywords")
    private Set<Paper> papers;
}
