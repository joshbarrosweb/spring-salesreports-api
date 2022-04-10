package com.joshbarrosweb.springsalesapi.formrequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joshbarrosweb.springsalesapi.entity.Client;

import java.time.LocalDate;

public class ClientFormRequest {

    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthdate;
    private String address;
    private String email;
    private String phone;
    private LocalDate createdAt;

    public ClientFormRequest() {
        super();
    }

    public ClientFormRequest(Long id, String name, String cpf, LocalDate birthdate, String address, String email, String phone, LocalDate createdAt) {
        super();
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Client toModel() {
        return new Client(id, birthdate, cpf, name, address, phone, email, createdAt);
    }

    public static ClientFormRequest fromModel(Client client) {
        return new ClientFormRequest(client.getId(), client.getName(),
                client.getCpf(), client.getBirthdate(), client.getAddress(),
                client.getEmail(), client.getPhone(), client.getCreatedAt());
    }
}
