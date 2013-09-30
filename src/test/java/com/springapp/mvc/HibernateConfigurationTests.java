package com.springapp.mvc;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

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

            session.save(user);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception ex) {
            assertTrue(false);
        }
        assertTrue(true);
    }
}