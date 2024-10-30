package com.example.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@Entity
@IdClass(Bookmark1.class)
@Table(name = "bookmark")
public class Bookmark {
    @Id
    @Column(name = "userid")
    private String userId;

    @Id
    @Column(name = "fdcid")
    private String fdcId;
    @Column
    private String description;
    @Column(name = "publisheddate")
    private LocalDate publishedDate;


    public Bookmark(String number, String number1) {
    }
}


