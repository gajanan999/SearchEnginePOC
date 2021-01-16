package com.searchengine;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.searchengine.controller.DataController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SearchEngineApplicationTests {
	
	@Autowired
	private DataController dataController;

	@Test
	void contextLoads() {
		assertThat(dataController).isNotNull();
	}
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	
	@Test
	public void searchDataShouldReturnDefaultvalues() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/searchData?keyword=google",
				String.class)).contains("google.com");
	}
}
