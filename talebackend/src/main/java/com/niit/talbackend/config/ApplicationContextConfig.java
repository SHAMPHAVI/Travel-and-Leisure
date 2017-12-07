package com.niit.talbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.talbackend.model.BillingAddress;
import com.niit.talbackend.model.Cart;
import com.niit.talbackend.model.Category;
import com.niit.talbackend.model.Order;
import com.niit.talbackend.model.Paymentdetails;
import com.niit.talbackend.model.Product;
import com.niit.talbackend.model.ShippingAddress;
import com.niit.talbackend.model.Supplier;
import com.niit.talbackend.model.User;

@Configuration
@ComponentScan("com.niit.talbackend")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	@Bean(name="datasource")
	public DataSource getH2DataSource(){
		DriverManagerDataSource datasource=new DriverManagerDataSource();
		datasource.setUrl("jdbc:h2:tcp://localhost/~/test");
		 datasource.setDriverClassName("org.h2.Driver");
		 datasource.setUsername("sa");
	    	datasource.setPassword("");
	    	return datasource;
	    	
	}
	  private Properties getHibernateProperties() {
	    	Properties properties = new Properties();
	        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	        properties.put("hibernate.hbm2ddl.auto", "update");
	    	return properties;
	    }
	   @Autowired
	    @Bean(name = "sessionFactory")
	    public SessionFactory getSessionFactory(DataSource dataSource) {
	    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	    	sessionBuilder.addProperties(getHibernateProperties());
	    	sessionBuilder.addAnnotatedClass(Category.class);
	    	sessionBuilder.addAnnotatedClass(Supplier.class);
	      	sessionBuilder.addAnnotatedClass(User.class);
	      	sessionBuilder.addAnnotatedClass(Product.class);
	      	sessionBuilder.addAnnotatedClass(BillingAddress.class);
	      	sessionBuilder.addAnnotatedClass(ShippingAddress.class);
	      	sessionBuilder.addAnnotatedClass(Cart.class);
	      	sessionBuilder.addAnnotatedClass(Order.class);
	      	sessionBuilder.addAnnotatedClass(Paymentdetails.class);




	    	return sessionBuilder.buildSessionFactory();
	    }
		@Autowired
		@Bean(name = "transactionManager")
		public HibernateTransactionManager getTransactionManager(
				SessionFactory sessionFactory) {
			HibernateTransactionManager transactionManager = new HibernateTransactionManager(
					sessionFactory);

			return transactionManager;
		}
}
