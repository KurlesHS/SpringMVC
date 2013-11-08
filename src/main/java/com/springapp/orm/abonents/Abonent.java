package com.springapp.orm.abonents;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 01.10.13
 * Time: 0:57
 */
@Entity
@Table(name = "ABONENT_DETAIL")
public class Abonent {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ABONENT_ID", unique = true, length = 36)
    private String abonentId;
    @Column(name = "ABONENT_NAME")
    private String abonentName;

    @OneToMany(mappedBy = "abonent", cascade = CascadeType.PERSIST)
    private Collection<PhoneNumber> listOfPhoneNumbers = new ArrayList<PhoneNumber>();

    @OneToMany(mappedBy = "abonent", cascade = CascadeType.PERSIST)
    private Collection<EMail> listOfEmails = new ArrayList<EMail>();

    public Collection<EMail> getListOfEmails() {
        return listOfEmails;
    }


    public Collection<PhoneNumber> getListOfPhoneNumbers() {
        return listOfPhoneNumbers;
    }

    public String getAbonentName() {
        return abonentName;
    }

    public void setAbonentName(String abonentName) {
        this.abonentName = abonentName;
    }
}
