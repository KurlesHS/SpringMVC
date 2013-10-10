package com.springapp.mvc;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import com.springapp.orm.EMail;
import com.springapp.orm.PhoneNumber;
import com.springapp.orm.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/persistence-beans.xml")
public class HibernateConfigurationTests extends AbstractJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testHibernateConfiguration() {
        // Spring IOC container instantiated and prepared sessionFactory
        assertNotNull (sessionFactory);
    }

    @Test
    public void testHibernate() {

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            User user = new User();
            user.setUserName("User Name");

            PhoneNumber number1 = new PhoneNumber();
            number1.setDescription("decr1");
            number1.setNumber("1234");

            PhoneNumber number2 = new PhoneNumber();
            number2.setDescription("descr2");
            number2.setNumber("9876");

            user.getListOfPhoneNumbers().add(number1);
            user.getListOfPhoneNumbers().add(number2);

            EMail eMail1 = new EMail();
            eMail1.setEmailAddress("ka@mail.com");
            eMail1.setDescription("descr1");

            EMail eMail2 = new EMail();

            eMail2.setEmailAddress("ka2@mail.com");
            eMail2.setDescription("descr2");

            user.getListOfEmails().add(eMail1);
            user.getListOfEmails().add(eMail2);

            session.persist(user);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception ex) {
            assertTrue(false);
        }
        assertTrue(true);
    }
}