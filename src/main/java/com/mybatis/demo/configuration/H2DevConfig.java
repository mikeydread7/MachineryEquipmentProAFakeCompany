package com.mybatis.demo.configuration;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration("H2DevConfig")
@Profile("h2")
public class H2DevConfig {

	@Bean
	public ServletRegistrationBean h2servletRegistration() {

		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/h2-console/*");

		// schema init
		/*
		 * Resource initSchema = new ClassPathResource("schema-h2.sql");
		 * //Resource initData = new ClassPathResource("scripts/data-h2.sql");
		 * DatabasePopulator databasePopulator = new
		 * ResourceDatabasePopulator(initSchema);
		 * DatabasePopulatorUtils.execute(databasePopulator, dataSource);
		 */

		return registrationBean;
	}
}
