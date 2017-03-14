package com.hatraa.hafifa.chat.model.hiberante;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class Factory {

    @Bean(name="SessionFactory")
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setPackagesToScan(new String [] { "com.hatraa.hafifa.chat.model" });
        sessionFactory.setHibernateProperties(Environment.getProperties());
        sessionFactory.setDataSource(getDataSource());

        return sessionFactory;
    }

    private DataSource getDataSource() {
        Properties properties = Environment.getProperties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(properties.getProperty("datasource.driver"));
        dataSource.setUrl(properties.getProperty("datasource.url"));
        dataSource.setUsername(properties.getProperty("datasource.username"));
        dataSource.setPassword(properties.getProperty("datasource.password"));

        return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }



    /*private static final LocalSessionFactoryBean sessionFactory;
    static {
        try {
            sessionFactory = new LocalSessionFactoryBean();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }*/

    /*@Bean(name = "session")
    Session sessionFactory() {
        return sessionFactory.openSession();
    }*/


    /*public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }*/
}
