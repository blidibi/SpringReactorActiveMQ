package com.lukman.reactoramq.javaconfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;

@Configuration
public class ReactorBean{
	
	@Bean
    public Environment env() {
        return new Environment();
    }

    @Bean
    public Reactor createReactor(Environment env) {
        return Reactors.reactor().env(env).dispatcher(Environment.RING_BUFFER).get();
    }
}