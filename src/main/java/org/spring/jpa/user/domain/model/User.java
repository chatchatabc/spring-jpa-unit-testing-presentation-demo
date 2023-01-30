package org.spring.jpa.user.domain.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    private String email;


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
