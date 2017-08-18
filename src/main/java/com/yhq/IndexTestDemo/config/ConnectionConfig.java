//package com.yhq.IndexTestDemo.config;
//
//import javax.sql.DataSource;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
///**
// * @author HuaQi.Yang
// * @date 2017年8月17日
// */
//@Configuration
//@ComponentScan(basePackages = { "com.yhq.IndexTestDemo.dao", "com.yhq.IndexTestDemo.service", "com.yhq.IndexTestDemo.controller" })
//public class ConnectionConfig {
//
//	@Bean
//	public DataSource dataSource() {
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setPassword("root");
//		dataSource.setUsername("root");
//		dataSource.setUrl(
//				"jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
//		dataSource.setMaxWaitMillis(60000);
//		dataSource.setInitialSize(10);
//		dataSource.setMinIdle(1);
//		dataSource.setMaxOpenPreparedStatements(20);
//		return dataSource;
//	}
//
//	@Bean
//	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
//		return dataSourceTransactionManager;
//	}
//	
//	@Bean 
//	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
//		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
//		sqlSessionFactory.setDataSource(dataSource);
//		return sqlSessionFactory;
//	}
//}
