package com.vinehds.dailysinc.model.entities;

import com.vinehds.dailysinc.model.enums.Department;
import com.vinehds.dailysinc.model.enums.Seniority;
import com.vinehds.dailysinc.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private UserRole role;

    @Column(name = "department")
    private Department department;

    @Column(name = "seniority")
    private Seniority seniority;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Daily> dailies = new ArrayList<>();


    public User(String email, String password, UserRole userRole) {
        this.password = password;
        this.email = email;
        this.role = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN)
            return
                    List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                            new SimpleGrantedAuthority("ROLE_TECHLEAD"),
                            new SimpleGrantedAuthority("ROLE_USER"));
        else if (this.role == UserRole.TECHLEAD)
            return
                    List.of(new SimpleGrantedAuthority("ROLE_TECHLEAD"),
                            new SimpleGrantedAuthority("ROLE_USER"));
        else return
                    List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return email; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
