package com.benfante.examples.searchable;

import org.springdoc.core.providers.ObjectMapperProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.yourrents.services.common.searchable.springdoc.SearchableOpenAPIConverter;
import com.yourrents.services.common.searchable.springdoc.customizer.SearchableOperationCustomizer;

@Configuration
public class SpringdocConfiguration {

	@Bean
	SearchableOpenAPIConverter searchableOpenAPIConverter(ObjectMapperProvider objectMapperProvider) {
		return new SearchableOpenAPIConverter(objectMapperProvider);
	}

	@Bean
	SearchableOperationCustomizer searchableOperationCustomizer() {
		return new SearchableOperationCustomizer();
	}
    
}