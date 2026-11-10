package Db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "citizens", schema = "reg_office")
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer citizenId;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "passportnumber")
    private String passportNumber;

    @Column(name = "dateofbirth")
    private String dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "registration_address")
    private String registrationAddress;

    public int getCitizenId() {
        return citizenId;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public Citizen setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Citizen setName(String name) {
        this.name = name;
        return this;
    }

    public Citizen setMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public Citizen setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
        return this;
    }

    public Citizen setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public Citizen setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Citizen setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
        return this;
    }
}
