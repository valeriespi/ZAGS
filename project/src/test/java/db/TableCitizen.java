package db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "citizens", schema = "reg_office")
public class TableCitizen {

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

    public TableCitizen setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public TableCitizen setName(String name) {
        this.name = name;
        return this;
    }

    public TableCitizen setMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public TableCitizen setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
        return this;
    }

    public TableCitizen setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public TableCitizen setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public TableCitizen setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
        return this;
    }
}
