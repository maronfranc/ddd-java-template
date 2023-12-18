package com.maron.ddd_java_sample.infrastructure.user;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Data
public class User {
    public User() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    @Getter
    @Setter
    private String id;
    @Column(nullable = false)
    @Getter
    @Setter
    private String email;
    @Column(nullable = false)
    @Getter
    @Setter
    private String password;
    @Column(nullable = false)
    @Getter
    @Setter
    private String salt;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Person person;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", person=" + person +
                '}';
    }
}
