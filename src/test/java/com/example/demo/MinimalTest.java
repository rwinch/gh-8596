package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.accept.RequestedContentTypeResolverBuilder;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.PathMatchConfigurer;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurerComposite;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

class MinimalTest {
	@Test
	void whenEnableWebFluxConfigurationThenItFailsWithCircularBeans() {
		createContext(ArgumentResolverConfig.class, EnableWebFluxConfiguration.class);
	}

	@Configuration(proxyBeanMethods = false)
	@EnableWebFlux
	static class EnableWebFluxConfiguration {}

	@Test
	void whenNoArgumentResolverThenSuccess() {
		createContext(EnableWebFluxConfiguration.class);
	}

	@Test
	void whenImportThenItFailsWithCircularBeans() {
		createContext(ArgumentResolverConfig.class, ImportDelegatingWebFluxConfiguration.class);
	}

	@Configuration
	@Import(DelegatingWebFluxConfiguration.class)
	static class ImportDelegatingWebFluxConfiguration {}

	@Test
	void whenExtendsDelegatingWebFluxConfigurationThenSuccess() {
		createContext(ArgumentResolverConfig.class, ExtendsDelegatingWebFluxConfiguration.class);
	}

	@Configuration
	static class ExtendsDelegatingWebFluxConfiguration extends DelegatingWebFluxConfiguration {}

	@Test
	void whenDelegatingWebFluxConfigurationThenWorks() {
		createContext(ArgumentResolverConfig.class, DelegatingWebFluxConfiguration.class);
	}

	@Test
	void whenMyDelegatingWebFluxConfigurationThenWorks() {
		createContext(ArgumentResolverConfig.class, MyDelegatingWebFluxConfiguration.class);
	}

	@Configuration(proxyBeanMethods = false)
	static class MyDelegatingWebFluxConfiguration extends WebFluxConfigurationSupport {

		private final WebFluxConfigurerComposite configurers = new WebFluxConfigurerComposite();

		@Autowired(required = false)
		public void setConfigurers(List<WebFluxConfigurer> configurers) {
			if (!CollectionUtils.isEmpty(configurers)) {
				this.configurers.addWebFluxConfigurers(configurers);
			}
		}

		@Override
		protected void configureContentTypeResolver(RequestedContentTypeResolverBuilder builder) {
			this.configurers.configureContentTypeResolver(builder);
		}

		@Override
		protected void addCorsMappings(CorsRegistry registry) {
			this.configurers.addCorsMappings(registry);
		}

		@Override
		public void configurePathMatching(PathMatchConfigurer configurer) {
			this.configurers.configurePathMatching(configurer);
		}

		@Override
		protected void addResourceHandlers(ResourceHandlerRegistry registry) {
			this.configurers.addResourceHandlers(registry);
		}

		@Override
		protected void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
			this.configurers.configureArgumentResolvers(configurer);
		}

		@Override
		protected void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
			this.configurers.configureHttpMessageCodecs(configurer);
		}

		@Override
		protected void addFormatters(FormatterRegistry registry) {
			this.configurers.addFormatters(registry);
		}

		@Override
		protected Validator getValidator() {
			Validator validator = this.configurers.getValidator();
			return (validator != null ? validator : super.getValidator());
		}

		@Override
		protected MessageCodesResolver getMessageCodesResolver() {
			MessageCodesResolver messageCodesResolver = this.configurers.getMessageCodesResolver();
			return (messageCodesResolver != null ? messageCodesResolver : super.getMessageCodesResolver());
		}

		@Override
		protected void configureViewResolvers(ViewResolverRegistry registry) {
			this.configurers.configureViewResolvers(registry);
		}
	}

	@Test
	void whenImportMyDelegatingWebFluxConfigurationThenFails() {
		createContext(ArgumentResolverConfig.class, ImportMyDelegatingWebFluxConfiguration.class);
	}

	@Import(MyDelegatingWebFluxConfiguration.class)
	@Configuration(proxyBeanMethods = false)
	static class ImportMyDelegatingWebFluxConfiguration {}

	private static void createContext(Class<?>... configs) {
		try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.register(configs);
			context.refresh();
		}
	}


	@Configuration(proxyBeanMethods = false)
	static class FixDelegatingWebFluxConfiguration extends WebFluxConfigurationSupport {

		private final WebFluxConfigurerComposite configurers = new WebFluxConfigurerComposite();

		@Autowired(required = false)
		public void setConfigurers(List<WebFluxConfigurer> configurers) {
			if (!CollectionUtils.isEmpty(configurers)) {
				this.configurers.addWebFluxConfigurers(configurers);
			}
		}

		@Override
		protected void configureContentTypeResolver(RequestedContentTypeResolverBuilder builder) {
			this.configurers.configureContentTypeResolver(builder);
		}

		@Override
		protected void addCorsMappings(CorsRegistry registry) {
			this.configurers.addCorsMappings(registry);
		}

		@Override
		public void configurePathMatching(PathMatchConfigurer configurer) {
			this.configurers.configurePathMatching(configurer);
		}

		@Override
		protected void addResourceHandlers(ResourceHandlerRegistry registry) {
			this.configurers.addResourceHandlers(registry);
		}

		@Override
		protected void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
			this.configurers.configureArgumentResolvers(configurer);
		}

		@Override
		protected void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
			this.configurers.configureHttpMessageCodecs(configurer);
		}

		@Override
		protected void addFormatters(FormatterRegistry registry) {
			this.configurers.addFormatters(registry);
		}

		@Override
		protected Validator getValidator() {
			Validator validator = this.configurers.getValidator();
			return (validator != null ? validator : super.getValidator());
		}

		@Override
		protected MessageCodesResolver getMessageCodesResolver() {
			MessageCodesResolver messageCodesResolver = this.configurers.getMessageCodesResolver();
			return (messageCodesResolver != null ? messageCodesResolver : super.getMessageCodesResolver());
		}

		@Override
		protected void configureViewResolvers(ViewResolverRegistry registry) {
			this.configurers.configureViewResolvers(registry);
		}
	}

	@Import(FixDelegatingWebFluxConfiguration.class)
	@Configuration(proxyBeanMethods = false)
	static class ImportFixDelegatingWebFluxConfiguration {}
}
