package Db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "applications", schema = "reg_office")
@DynamicInsert
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationId;

    @Column(name = "citizenid")
    private Integer citizenId;

    @Column(name = "applicantid")
    private Integer applicantId;

    @Column(name = "kindofapplication")
    private String kindOfApplication;

    @Column(name = "statusofapplication")
    private String statusOfApplication;

    @Column(name = "channel")
    private String channel;

    public int getApplicationId() {
        return applicationId;
    }

    public Integer getCitizenId() {
        return citizenId;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public String getKindOfApplication() {
        return kindOfApplication;
    }

    public String getStatusOfApplication() {
        return statusOfApplication;
    }

    public String getChannel() {
        return channel;
    }

    public Application setCitizenId(Integer citizenId) {
        this.citizenId = citizenId;
        return this;
    }

    public Application setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
        return this;
    }

    public Application setKindOfApplication(String kindOfApplication) {
        this.kindOfApplication = kindOfApplication;
        return this;
    }

    public Application setChannel(String channel) {
        this.channel = channel;
        return this;
    }
}
