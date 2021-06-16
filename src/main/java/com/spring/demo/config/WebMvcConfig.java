package com.spring.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private static final String[] RESOURCE_LOCATIONS = {
			"classpath:/",
			"classpath:/static/",
			"classpath:/public/",
			"classpath:/resources/",
			"classpath:/META_INF/resources",
			"classpath:/META_INF/webjars/"
	};
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// "/" 맵핑 url을 index로 포워딩. 
		registry.addViewController("/").setViewName("forward:/index");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//정적resource locations 설정. 
		registry.addResourceHandler("/**").addResourceLocations(RESOURCE_LOCATIONS);
	}
	
	
}
