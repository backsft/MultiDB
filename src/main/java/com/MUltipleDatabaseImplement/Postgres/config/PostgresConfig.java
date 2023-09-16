package com.MUltipleDatabaseImplement.Postgres.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "PostgresFactoryBean",
		basePackages = { "com.MUltipleDatabaseImplement.Postgres.familyRepo" }, 
		transactionManagerRef = "PostgresTransaction"

)

public class PostgresConfig {


	@Primary
	@Bean(name = "PostgresDataSource")
	
	 DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/familyinformation");
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUsername("postgres");
		dataSource.setPassword("root");
		
		

		return dataSource;

	}
	

	@Primary
	@Bean(name = "PostgresFactoryBean")
	
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		bean.setJpaVendorAdapter(adapter);
		Map<String, String> props = new HashMap<>();
		
		props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");

		bean.setJpaPropertyMap(props);
		bean.setPackagesToScan("com.MUltipleDatabaseImplement.Postgres.familyEntity");

		return bean;

	}

	@Primary
	@Bean(name = "PostgresTransaction")
	

	PlatformTransactionManager platformTransactionManager() {
		JpaTransactionManager manger = new JpaTransactionManager();

		manger.setEntityManagerFactory(entityManagerFactoryBean().getObject());

		return manger;

	}

}
