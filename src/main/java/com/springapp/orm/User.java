package com.springapp.orm;

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
@Table(name = "USER_DETAIL")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "USER_ID", unique = true, length = 36)
    private String userId;
    @Column(name = "USER_NAME")
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Collection<PhoneNumber> listOfPhoneNumbers = new ArrayList<PhoneNumber>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Collection<EMail> listOfEmails = new ArrayList<EMail>();

    public Collection<EMail> getListOfEmails() {
        return listOfEmails;
    }


    public Collection<PhoneNumber> getListOfPhoneNumbers() {
        return listOfPhoneNumbers;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
