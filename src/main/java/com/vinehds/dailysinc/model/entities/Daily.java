package com.vinehds.dailysinc.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_dailies")
public class Daily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Developer author;

    private LocalDate date;

    @Column(columnDefinition = "TEXT", length = 500)
    private String lastDayLog;

    @Column(columnDefinition = "TEXT", length = 500)
    private String nextDayPlan;

    @Column(columnDefinition = "TEXT", length = 500)
    private String blockers;
}
