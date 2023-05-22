package com.BikkadIT.CrudOperation.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClass {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
