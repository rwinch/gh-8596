package com.example.demo;

import reactor.core.publisher.Mono;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author Rob Winch
 */
@Configuration(proxyBeanMethods = false)
public class ArgumentResolverConfig {

	@Bean
	public WebFluxConfigurer authenticationPrincipalArgumentResolverConfigurer(
			AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver) {
		return new WebFluxConfigurer() {
			@Override
			public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
				configurer.addCustomResolver(authenticationPrincipalArgumentResolver);
			}
		};
	}

	@Bean
	public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver(ReactiveAdapterRegistry adapterRegistry) {
		return new AuthenticationPrincipalArgumentResolver(adapterRegistry);
	}

	static class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {
		public AuthenticationPrincipalArgumentResolver(ReactiveAdapterRegistry adapterRegistry) {

		}

		@Override
		public boolean supportsParameter(MethodParameter parameter) {
			return false;
		}

		@Override
		public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
			return null;
		}
	}
}
