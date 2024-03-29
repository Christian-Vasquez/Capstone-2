package com.company.adminservice.util.message;

import java.util.Objects;

public class CustomerClient {

    private int id;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private String email;
    private String phone;

    public CustomerClient() {
    }

    public CustomerClient(int id, String firstName, String lastName, String street, String city, String zip, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerClient)) return false;
        CustomerClient customerClient = (CustomerClient) o;
        return id == customerClient.id &&
                Objects.equals(firstName, customerClient.firstName) &&
                Objects.equals(lastName, customerClient.lastName) &&
                Objects.equals(street, customerClient.street) &&
                Objects.equals(city, customerClient.city) &&
                Objects.equals(zip, customerClient.zip) &&
                Objects.equals(email, customerClient.email) &&
                Objects.equals(phone, customerClient.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, street, city, zip, email, phone);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

