package com.example.demo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.resource.ResourceUrlProvider;

/**
 * @author Rob Winch
 */
@SpringBootTest
class DemoApplicationTest {
	@Test
	void loads() {}

	@Test
	void loads(@Autowired ResourceUrlProvider resourceUrlProvider) {}
}
