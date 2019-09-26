package com.cts.postal.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
@ComponentScan(basePackages={"com.cts.postal.*"})
public class AppContext {

	@Autowired
	Environment ev;
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] {"com.cts.postal.*"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	@Bean
	public HibernateTransactionManager getTransactionManager(){
		HibernateTransactionManager htm=new HibernateTransactionManager();
		htm.setSessionFactory(sessionFactory().getObject());
		return htm;
	}
	
	public Properties hibernateProperties(){
		Properties prop=new Properties();
		prop.put("hibernate.dialect",ev.getRequiredProperty("hibernate.dialect"));
		prop.put("hibernate.show_sql",ev.getRequiredProperty("hibernate.show_sql"));
		prop.put("hibernate.format_sql",ev.getRequiredProperty("hibernate.format_sql"));
		prop.put("hibernate.hbm2ddl.auto",ev.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return prop;
	}
	public DataSource dataSource(){
		DriverManagerDataSource dm=new DriverManagerDataSource();
		dm.setDriverClassName(ev.getRequiredProperty("jdbc.driverClassName"));
		
		dm.setUrl(ev.getRequiredProperty("jdbc.url"));
		dm.setUsername(ev.getRequiredProperty("jdbc.username"));
		dm.setPassword(ev.getRequiredProperty("jdbc.password"));
		return dm;
	}
	
	
}
