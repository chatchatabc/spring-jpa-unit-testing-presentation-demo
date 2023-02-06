package org.spring.jpa.user.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    /**
     * password hash with SHA-1: plain password + salt
     */
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "salt", nullable = false)
    private String salt;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

}
