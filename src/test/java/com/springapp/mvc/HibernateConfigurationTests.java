package com.springapp.mvc;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import com.springapp.orm.abonents.Abonent;
import com.springapp.orm.abonents.EMail;
import com.springapp.orm.abonents.PhoneNumber;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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

            Abonent abonent = new Abonent();
            abonent.setAbonentName("User Name");

            PhoneNumber number1 = new PhoneNumber();
            number1.setDescription("decr1");
            number1.setNumber("1234");

            PhoneNumber number2 = new PhoneNumber();
            number2.setDescription("descr2");
            number2.setNumber("9876");

            abonent.getListOfPhoneNumbers().add(number1);
            abonent.getListOfPhoneNumbers().add(number2);

            EMail eMail1 = new EMail();
            eMail1.setEmailAddress("ka@mail.com");
            eMail1.setDescription("descr1");

            EMail eMail2 = new EMail();

            eMail2.setEmailAddress("ka2@mail.com");
            eMail2.setDescription("descr2");

            abonent.getListOfEmails().add(eMail1);
            abonent.getListOfEmails().add(eMail2);

            session.persist(abonent);

            //assertEquals("User Name", eMail2.getAbonent().getAbonentName());

            session.getTransaction().commit();

            session.getTransaction().begin();

            List<EMail> eMailList;
            eMailList = session.createCriteria(EMail.class).list();
            assertEquals(2, eMailList.size());
            assertNotNull(eMailList.get(0).getAbonent());
            assertEquals("User Name", eMailList.get(0).getAbonent().getAbonentName());
            session.close();
        }  catch (Exception ex) {
            assertTrue(false);
        }
        assertTrue(true);
    }
}