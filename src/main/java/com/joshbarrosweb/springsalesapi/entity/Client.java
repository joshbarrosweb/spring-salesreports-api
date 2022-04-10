package com.joshbarrosweb.springsalesapi.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate birthdate;

    private String cpf;

    private String name;

    private String address;

    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public Client() {
        super();
    }

    public Client(Long id, LocalDate birthdate, String cpf, String name, String address, String phone, String email, LocalDate createdAt) {
        super();
        this.id = id;
        this.birthdate = birthdate;
        this.cpf = cpf;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Client(LocalDate birthdate, String cpf, String name, String address, String phone, String email) {
        super();
        this.birthdate = birthdate;
        this.cpf = cpf;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    @PrePersist
    public void prePersist() {
        setCreatedAt(LocalDate.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", birthdate=" + birthdate +
                ", cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
