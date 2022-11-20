package com.zero1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
	@Bean
	public CallString callString() {
		CallString cs = new CallString();
		return cs;
	}
}