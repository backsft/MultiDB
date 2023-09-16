package com.MUltipleDatabaseImplement.MYSQL.config;

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
		entityManagerFactoryRef = "MysqlFactoryBean",
		basePackages = { "com.MUltipleDatabaseImplement.MYSQL.MysqlRepo" }, 
		transactionManagerRef = "mysqlTransaction"

)

public class MySQLConfig {


	@Primary
	@Bean(name = "MysqlDataSource")
	
	 DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/studeninformationDB");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		

		return dataSource;

	}

	@Primary
	@Bean(name = "MysqlFactoryBean")
	
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		bean.setJpaVendorAdapter(adapter);
		Map<String, String> props = new HashMap<>();
		
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");

		bean.setJpaPropertyMap(props);
		bean.setPackagesToScan("com.MUltipleDatabaseImplement.MYSQL.Entities");

		return bean;

	}

	@Primary
	@Bean(name = "mysqlTransaction")
	

	PlatformTransactionManager platformTransactionManager() {
		JpaTransactionManager manger = new JpaTransactionManager();

		manger.setEntityManagerFactory(entityManagerFactoryBean().getObject());

		return manger;

	}

}
