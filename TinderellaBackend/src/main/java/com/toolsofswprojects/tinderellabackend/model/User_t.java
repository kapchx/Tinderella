package com.toolsofswprojects.tinderellabackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User_t implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String userName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String imageUrl;

    @Column(nullable = false, updatable = false)
    private String userCode;

    @Column
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public enum UserRole {
        ROLE_GUEST, ROLE_USER, ROLE_ADMIN
    }

    public User_t() {
    }

    public User_t(Long id, String name, String email, String phone, String imageUrl, String userCode, String password, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.userCode = userCode;
        this.password = password;
        this.role = role;
    }
    public String getUserName() { return userName; }

    public UserRole getRole() { return role; }

    public String getPassword() { return password; }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUserCode() {
        return userCode;
    }


    public void setUserName(String userName) { this.userName = userName; }

    public void setRole(UserRole role) { this.role = role; }

    public void setPassword(String password) { this.password = password; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "User_t{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", userCode='" + userCode + '\'' +
                '}';
    }
}
