package Db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "staff", schema = "reg_office")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffId;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    @Column(name = "passportnumber")
    private String passportNumber;

    @Column(name = "phonenumber")
    private String phoneNumber;

    public int getStaffId() {
        return staffId;
    }

    public Staff setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Staff setName(String name) {
        this.name = name;
        return this;
    }

    public Staff setMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public Staff setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public Staff setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
        return this;
    }

    public Staff setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
