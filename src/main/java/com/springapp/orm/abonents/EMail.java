package com.springapp.orm.abonents;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 10.10.13
 * Time: 0:57
 */
@Entity
@Table(name = "EMAIL")
public class EMail {
    @Id
    @Column(name = "EMAIL_ID", length = 36, unique = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "EMAIL_DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "ABONENT_ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private Abonent abonent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Abonent getAbonent() {
        return abonent;
    }

    public void setAbonent(Abonent abonent) {
        this.abonent = abonent;
    }
}
