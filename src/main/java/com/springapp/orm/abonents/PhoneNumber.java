package com.springapp.orm.abonents;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 10.10.13
 * Time: 0:20
 */
@Entity
@Table(name = "PHONES")
public class PhoneNumber {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "PHONE_ID", length = 36, unique = true)
    private String id;

    @Column(name = "PHONE_NUMBER")
    private String number;

    @Column(name = "PHONE_DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "ABONENT_ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private Abonent abonent;

    public Abonent getAbonent() {
        return abonent;
    }

    public void setAbonent(Abonent abonent) {
        this.abonent = abonent;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
