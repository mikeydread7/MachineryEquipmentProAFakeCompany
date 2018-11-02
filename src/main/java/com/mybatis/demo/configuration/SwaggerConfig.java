package com.mybatis.demo.configuration;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.common.base.Predicate;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.scanners.ApiListingReferenceScanner;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiTAllowAll() {
		ModelRef errorModel = new ModelRef("RestApiExceptionModel");
		ModelRef createOKModel = new ModelRef("RestApiCreatedOrOknModel");
	    List<ResponseMessage> responseMessages = Arrays.asList(
	            new ResponseMessageBuilder().code(401).message(UNAUTHORIZED.getReasonPhrase()).responseModel(errorModel).build(),
	            new ResponseMessageBuilder().code(403).message(FORBIDDEN.getReasonPhrase()).responseModel(errorModel).build(),
	            new ResponseMessageBuilder().code(404).message(NOT_FOUND.getReasonPhrase()).responseModel(errorModel).build(),
	            new ResponseMessageBuilder().code(204).message(NO_CONTENT.getReasonPhrase()).responseModel(errorModel).build()
	            );
	    	    
	    List<ResponseMessage> responseMessagesOk =  Arrays.asList(
	            new ResponseMessageBuilder().code(401).message(UNAUTHORIZED.getReasonPhrase()).responseModel(errorModel).build(),
	            new ResponseMessageBuilder().code(403).message(FORBIDDEN.getReasonPhrase()).responseModel(errorModel).build(),
	            new ResponseMessageBuilder().code(404).message(NOT_FOUND.getReasonPhrase()).responseModel(errorModel).build(),
	            new ResponseMessageBuilder().code(204).message(NO_CONTENT.getReasonPhrase()).responseModel(errorModel).build(),
	            new ResponseMessageBuilder().code(200).message(OK.getReasonPhrase()).responseModel(createOKModel).build());
	    List<ResponseMessage> responseMessagesCreated =  Arrays.asList(
	            new ResponseMessageBuilder().code(401).message(UNAUTHORIZED.getReasonPhrase()).responseModel(errorModel).build(),
	            new ResponseMessageBuilder().code(403).message(FORBIDDEN.getReasonPhrase()).responseModel(errorModel).build(),
	            new ResponseMessageBuilder().code(404).message(NOT_FOUND.getReasonPhrase()).responseModel(errorModel).build(),
	            new ResponseMessageBuilder().code(204).message(NO_CONTENT.getReasonPhrase()).responseModel(errorModel).build(),
	            new ResponseMessageBuilder().code(201).message(CREATED.getReasonPhrase()).responseModel(createOKModel).build());
	   
	    return new Docket(DocumentationType.SWAGGER_2)
	            .groupName("full-machine-company-api")
	            .apiInfo(apiInfo())
	            .select()
	            .paths(customerControllerPaths()) // show just a limited amout of end points
	            .build()
				.useDefaultResponseMessages(false)                                   
				.globalResponseMessage(RequestMethod.POST, responseMessagesCreated)
		        .globalResponseMessage(RequestMethod.PUT, responseMessagesCreated)
		        .globalResponseMessage(RequestMethod.GET, responseMessagesOk)
		        .globalResponseMessage(RequestMethod.DELETE, responseMessages)
		         
		        ;    
	}
	
	@SuppressWarnings("unchecked")
	/*
	 * Restrict this to just some
	 * we can also add more restriction using spring security + oauth() security
	 * 
	 */
	private Predicate<String> customerControllerPaths() {
		return or(
		        regex("/mybatis/order/id/.*"),
		        regex("/mybatis/product/id/.*"),
		        regex("/mybatis/user/id/.*|^/mybatis/user$|[^/mybatis/user/$]")
		);
	}
	
	private ApiInfo apiInfo() {
		String title = "My Fake Equipment Machine Application REST API";
	    String descr = "Provide a API access for Approved users";
	    String version = "1.002";
	    String termsUrl = "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND";
	    Contact contact = new Contact("Michael Somers", "mywww.ibatis-example.com","mike1somers@gmail.com");
	    String license = "License of API";
	    String licenseUrl ="https://github.com/springfox/springfox/blob/master/LICENSE"; 
		return new ApiInfo(title, descr, version, termsUrl, contact, license, licenseUrl, Collections.emptyList());
	}

	@Bean
	public ApiListingReferenceScanner apiListingReferenceScanner() {
		ApiListingReferenceScanner apiListingReferenceScanner = new ApiListingReferenceScanner();
		//we should populate this but I just want to get rid of a deprecated warnning
		//see HoWTo github.com/springfox/springfox/blob/master/.. howto populate  the ResourceMessages HTTP verbs.. I am LAZY!!
		//apiListingReferenceScanner.setResourceGroupingStrategy(SpringSwaggerConfig.defaultResourceGroupingStrategy());
		return apiListingReferenceScanner;
	}

}
