package com.dev.web.blogging.entity;

import com.dev.web.blogging.util.BcryptUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private int active;

    public User(String userId, String password, int active) {
        this.userId = userId;
        this.password = password;
        this.active = active;
    }

    public void setPassword(String password) {
        this.password = BcryptUtil.generateHash(password, 10);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", active='" + active + '\'' +
                '}';
    }
}
