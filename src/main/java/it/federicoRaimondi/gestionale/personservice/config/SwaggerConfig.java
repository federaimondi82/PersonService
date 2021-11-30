package it.federicoRaimondi.gestionale.personservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Classe di configurazione per lo Swagger. Lo Swagger consente di visualizzare
 * sul web le API REST di questo servizio <a href=
 * "http://localhost:5051/swagger-ui.html">http://localhost:5052/swagger-ui.html<a/>
 * 
 * @author Federico Raimondi
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/*
	 * URL Swagger: http://localhost:8080/swagger-ui.html Docs:
	 * https://swagger.io/docs/
	 */

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("PersonService")
				.description("Servizio per la gestione di PersonService. Salvataggio/modifica e cancellazione.")
				.version("1.0.0").license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0").contact(new Contact("Federico Raimondi",
						"https://www.studionotturno.net", "federicoraimondi@studenti.unicam.it"))
				.build();
	}

}
