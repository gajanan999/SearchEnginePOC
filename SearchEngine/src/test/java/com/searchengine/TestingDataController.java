package com.searchengine;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.searchengine.service.PageService;


@SpringBootTest
@AutoConfigureMockMvc
public class TestingDataController {
	
	@Autowired
	private MockMvc mockMvc;
	

	@MockBean
	private PageService pageService;
	

	@Test
	public void getSearchDatashouldReturnDefaultResult() throws Exception {
		
		List<String> list = new ArrayList<>();
		list.add("google.com");
		
		when(pageService.getAllRelatedPages("google")).thenReturn(list);
		this.mockMvc.perform(get("/getSearchData?keyword=google")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("google")));
	}
	
	

	@Test
	public void getSearchDatashouldReturnDatAssigned() throws Exception {
		
		List<String> list = new ArrayList<>();
		list.add("linkedin.com");
		
		when(pageService.getAllRelatedPages("li")).thenReturn(list);
		this.mockMvc.perform(get("/getSearchData?keyword=li")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("linked")));
	}
	

}
