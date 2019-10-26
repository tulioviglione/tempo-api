package com.calculo.tempo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.calculo.tempo.security.utils.JwtTokenUtil;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Profile("dev")//configurado somente para utilização no ambiente de desenvolvimento
@EnableSwagger2
public class SwaggerConfig {

//	@Autowired
//	private JwtTokenUtil jwtTokenUtil;
//
//	@Autowired
//	private UserDetailsService userDetailsService;

	/**
	 * configuração
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(Predicates.or(RequestHandlerSelectors.basePackage("com.calculo.tempo.controllers"),
						RequestHandlerSelectors.basePackage("com.calculo.tempo.security.controllers")))
				.paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Calculo Tempo API")
				.description("Documentação sistema calculo de tempo.").version("1.0")
				.build();
	}

	/**
	 * cria token para acesso
	 * @return
	 */
//	@Bean
//	public SecurityConfiguration security() {
//		String token;
//		try {
//			UserDetails userDetails = this.userDetailsService.loadUserByUsername("admin@admin.com");
//			token = this.jwtTokenUtil.obterToken(userDetails);
//		} catch (Exception e) {
//			token = "";
//		}
//
//		return new SecurityConfiguration(null, null, null, null, "Bearer " + token, ApiKeyVehicle.HEADER,
//				"Authorization", ",");
//	}

}
