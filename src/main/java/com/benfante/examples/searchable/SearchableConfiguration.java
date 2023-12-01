package com.benfante.examples.searchable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yourrents.services.common.searchable.config.SearchableArgumentResolverConfigurer;

@Configuration
public class SearchableConfiguration {
    
	@Bean
	public SearchableArgumentResolverConfigurer searchableArgumentResolverConfigurer() {
		return new SearchableArgumentResolverConfigurer();
	}

}