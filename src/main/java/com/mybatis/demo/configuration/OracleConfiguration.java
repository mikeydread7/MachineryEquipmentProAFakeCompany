package com.mybatis.demo.configuration;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConfigurationProperties("oracle")
@Profile("!h2")
public class OracleConfiguration {

	@Value("${spring.datasource.driver-class-name}")
	private String driverClass;
	@Value("${spring.datasource.url}")
	private String jdbcUrl;
	@Value("${spring.datasource.username}")
	private String jdbcUsername;
	@Value("${spring.datasource.password}")
	private String jdbcPassword;

	@Value("${spring.datasource.hikari.minimum-idle}")
	private int minimumIdle;
	@Value("${spring.datasource.hikari.maximum-pool-size}")
	private int maximumPoolSize;
	@Value("${spring.datasource.hikari.connection-timeout}")
	private int connectionTimeout;
	@Value("${spring.datasource.hikari.connection-test-query}")
	private String validationQuery;
	@Value("${spring.datasource.hikari.auto-commit}")
	private boolean isAutoCommit;
	@Value("${spring.datasource.hikari.poolName}")
	private String poolName;

	@Value("${datasource.cachePrepStmts}")
	private String cachePrepStmts;
	@Value("${datasource.prepStmtCacheSize}")
	private String prepStmtCacheSize;
	@Value("${datasource.prepStmtCacheSqlLimit}")
	private String prepStmtCacheSqlLimit;
	@Value("${datasource.useServerPrepStmts}")
	private String useServerPrepStmts;

	private HikariConfig getHikariConfig() {
		HikariConfig config = new HikariConfig();
		config.setPoolName(poolName);
		config.setMinimumIdle(minimumIdle);
		config.setMaximumPoolSize(maximumPoolSize);
		config.setConnectionTestQuery(validationQuery);
		config.setConnectionTimeout(connectionTimeout);
		config.setAutoCommit(isAutoCommit);

		config.addDataSourceProperty("cachePrepStmts", cachePrepStmts);
		config.addDataSourceProperty("prepStmtCacheSize", prepStmtCacheSize);
		config.addDataSourceProperty("useServerPrepStmts", useServerPrepStmts);

		return config;
	}

	public HikariDataSource hikariDataSource() {
		HikariConfig config = getHikariConfig();
		config.setDriverClassName(driverClass);
		config.addDataSourceProperty("user", jdbcUsername);
		config.addDataSourceProperty("password", Crendentials.getSecurePasswordFromVault());
		config.setJdbcUrl(jdbcUrl);
		return new HikariDataSource(config);
	}

	@Bean
	DataSource dataSource() throws SQLException {

		return hikariDataSource();
	}

	// short alternative way but not much control
	/*
	 * @ConfigurationProperties(prefix="spring") public DataSource
	 * secondaryDataSource() { return DataSourceBuilder.create().build(); }
	 */
}
