package org.spring.jpa.user.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @Size(min = 3, max = 20, message = "Username must be between 3 to 20 characters long")
    @Pattern(regexp = "^[a-fA-F0-9]{40}$", message = "Username must have no special characters")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * password hash with SHA-1: plain password + salt
     */
    @Column(name = "password", nullable = false)
    @Pattern(regexp = "^[a-fA-F0-9]{40}$", message = "Password must be SHA-1 hash")
    @Size(min = 3, max = 15, message = "Password must be between 6 to 15 characters long")
    private String password;

    @Size(min = 5, max = 10, message = "salt must be between 5 to 10 characters long")
    @Column(name = "salt", nullable = false)
    @Pattern(regexp = "^[a-fA-F0-9]{40}$", message = "salt must have no special characters")
    private String salt;

    @Email
    @Size(min = 10, max = 20, message = "Email must be between 10 to 20 characters long")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

}
