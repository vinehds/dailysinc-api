package com.vinehds.dailysinc.model.entities;

import com.vinehds.dailysinc.model.enums.Department;
import com.vinehds.dailysinc.model.enums.Seniority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_developers")
public class Developer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "dev_department")
    private Department department;

    @Column(name = "dev_seniority")
    private Seniority seniority;

    @Column(name = "dev_active")
    private Boolean active;

    @Column(name = "dev_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Daily> dailies = new ArrayList<>();
}
