package gov.usps.eir9361.testutility.ipmismocka.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import gov.usps.eir9361.testutility.ipmismocka.application.IpmisMockaApplication;
import gov.usps.eir9361.testutility.ipmismocka.models.ParametersBean;
import gov.usps.eir9361.testutility.ipmismocka.models.TangoServiceMessage;
import gov.usps.eir9361.testutility.ipmismocka.services.MockaService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MockaController.class)
@ContextConfiguration(classes = {IpmisMockaApplication.class})
@AutoConfigureMockMvc
public class MockaControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ParametersBean parameters;
    
    @MockitoBean
    private MockaService service;
    

    @Test
    public void testPostEnableSuccess() throws Exception {
        mockMvc.perform(post("/enable")
		        .contentType("text/plain")
		        .content("01"))
        		.andExpect(status().isCreated());
    }
    
    @Test
    public void testGetSuccess() throws Exception {
    	
    	parameters.setTestType("01");
    	
    	 when(service.buildMessage("GET"))
				.thenReturn(new TangoServiceMessage("OK", "Complete", "Valid_Guid"));
    	
        mockMvc.perform(get(""))
        		.andExpect(status().isOk())
        		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.status_message").value("Complete"))
                .andExpect(jsonPath("$.gu_id").value("Valid_Guid"));
    }
    
    @Test
    public void testGetFailureServerError() throws Exception {
    	
    	parameters.setTestType(null);
    	
    	 mockMvc.perform(get(""))
 		.andExpect(status().is5xxServerError());
    	
    }
    
    @Test
	public void testPutSuccess() throws Exception {

		parameters.setTestType("01");

		when(service.buildMessage("PUT")).thenReturn(new TangoServiceMessage("OK", "Complete", "Valid_Guid"));

        mockMvc.perform(put(""))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.status").value("OK"))
		        .andExpect(jsonPath("$.status_message").value("Complete"))
		        .andExpect(jsonPath("$.gu_id").value("Valid_Guid"));

	}
    
}
