package com.almis.fmb.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  @Value("${security.oauth2.client.client-id}")
  private String clientId;

  @Value("${security.oauth2.client.client-secret}")
  private String clientSecret;

  @Value("${security.oauth2.server.token-url}")
  private String tokenUrl;


  /**
   *
   * @return Docket
   */

  @Bean
  public Docket authApi() {
    return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("com.almis.fmb.auth.controller"))
            .paths(PathSelectors.any()).build()
            .securityContexts(Collections.singletonList(securityContext()))
            .securitySchemes(Collections.singletonList(securitySchema())).apiInfo(apiInfo());
  }


  private OAuth securitySchema() {
    List<AuthorizationScope> authorizationScopeList = newArrayList();
    authorizationScopeList.add(new AuthorizationScope("READ", "read all"));
    authorizationScopeList.add(new AuthorizationScope("WRITE", "access all"));
    List<GrantType> grantTypes = newArrayList();
    GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(tokenUrl);
    grantTypes.add(passwordCredentialsGrant);

    return new OAuth("oauth2", authorizationScopeList, grantTypes);
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth()).build();
  }

  private List<SecurityReference> defaultAuth() {

    final AuthorizationScope[] authorizationScopes = new AuthorizationScope[2];
    authorizationScopes[0] = new AuthorizationScope("READ", "read all");
    authorizationScopes[1] = new AuthorizationScope("WRITE", "write all");
    return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
  }

  @Bean
  public SecurityConfiguration security() {
    return new SecurityConfiguration(clientId, clientSecret, "", "", "Bearer access token", ApiKeyVehicle.HEADER,
            HttpHeaders.AUTHORIZATION, "");
  }

  /**
   *
   * @return ApiInfo
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("AUTH MICROSERVICE").description("Author by Pablo Vidal")
            .termsOfServiceUrl("https://github.com/limkinZero")
            .contact(new Contact("Pablo Vidal", "https://www.almis.com/", "pablo.vidal@almis.com"))
            .license("Open Source").licenseUrl("https://github.com/limkinZero/advance-spring-boot-microservice").version("1.0.0").build();

  }

}
