package com.mybatis.demo.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages = { "com.mybatis.demo.mapper" })
@EnableTransactionManagement(mode = AdviceMode.PROXY, order = 0)
public class MyBatisConfig {
	private static final Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		logger.debug("Setting SqlSessionFactory");
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		return sessionFactory.getObject();
	}

	@Bean(destroyMethod = "clearCache")
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		logger.debug("Setting SqlSessionTemplate");
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	@Profile("!h2")
	public DataSourceTransactionManager transactionManager(DataSource dataSource) throws SQLException {
		return new DataSourceTransactionManager(new LazyConnectionDataSourceProxy(dataSource));
	}

}
