package com.remy.jirastream.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
      "classpath:/META-INF/resources/", "classpath:/resources/",
      "classpath:/static/", "classpath:/public/"};

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!registry.hasMappingForPattern("/**")) {
      registry.addResourceHandler("/**")
          .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    // Forward requests to the application root ("/jira-stream/") to the index.html
    registry.addViewController("/jira-stream/").setViewName("forward:/index.html");

    // Forward any non-api and non-login request within the context path to the index.html
    registry.addViewController("/jira-stream/{x:^(?!api|login$).*$}/**").setViewName("forward:/index.html");
  }
}