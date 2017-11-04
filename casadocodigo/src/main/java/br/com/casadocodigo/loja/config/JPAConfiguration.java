package br.com.casadocodigo.loja.config;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		factory.setJpaVendorAdapter(jpaVendorAdapter);

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		// dataSource.setUsername("tomcat");
		// dataSource.setPassword("root@123");
		dataSource.setUrl(
				"jdbc:sqlserver://naasp.database.windows.net:1433;database=naasp;user=tomcat;password=Root@123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		factory.setPersistenceXmlLocation("");
		factory.setDataSource(dataSource);

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.hbm2dll.auto", "update");
		factory.setJpaProperties(properties);

		factory.setPackagesToScan("br.com.casadocodigo.loja.models");

		return factory;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}
}
