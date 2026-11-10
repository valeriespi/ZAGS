package Db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "applicants", schema = "reg_office")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicantId;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "passportnumber")
    private String passportNumber;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "registration_address")
    private String registrationAddress;

    public int getApplicantId() {
        return applicantId;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public Applicant setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Applicant setName(String name) {
        this.name = name;
        return this;
    }

    public Applicant setMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public Applicant setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
        return this;
    }

    public Applicant setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Applicant setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
        return this;
    }
}
