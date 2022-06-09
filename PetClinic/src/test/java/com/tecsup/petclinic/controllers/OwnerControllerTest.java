package com.tecsup.petclinic.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.dto.OwnerDTO;


@AutoConfigureMockMvc
@SpringBootTest
public class OwnerControllerTest {
	private static final Logger logger
		= LoggerFactory.getLogger(OwnerControllerTest.class);
	private static final ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testCreateOwner() throws Exception{
		String FIRST_NAME = "George";
		String LAST_NAME = "Franklin";
		String CITY  = "Madison";
		
		OwnerDTO newOwner = new OwnerDTO(FIRST_NAME, LAST_NAME, CITY);
		logger.info(newOwner.toString());
		logger.info(om.writeValueAsString(newOwner));
		
		mockMvc.perform(post("/owners")
				.content(om.writeValueAsString(newOwner))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.first_name", is(FIRST_NAME)))
				.andExpect(jsonPath("$.last_name", is(LAST_NAME)))
				.andExpect(jsonPath("$.city", is(CITY)));
		
	}	
}