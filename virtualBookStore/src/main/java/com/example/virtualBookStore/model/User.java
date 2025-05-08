package com.example.virtualBookStore.model;

import com.example.virtualBookStore.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    //USERDETAILS:

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }

    @Override public String getUsername(){return email;}
    @Override public String getPassword(){return password;}

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
