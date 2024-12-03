package gov.usps.eir9361.testutility.ipmismocka.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import gov.usps.eir9361.testutility.ipmismocka.models.ParametersBean;
import gov.usps.eir9361.testutility.ipmismocka.models.TangoServiceMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MockaService {
	
	private final ParametersBean parameters;

	public TangoServiceMessage buildMessage(String httpSemantic) {
		
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		int versionIndex = parameters.getContextPath().indexOf("/v1/");
		String context = parameters.getContextPath().substring(versionIndex + "/v1/".length());
		
		log.debug(context);
		
		String messageString = parameters.getApiTypeMap().get(context+httpSemantic+parameters.getTestType());

		if (messageString != null) {
			String[] messageFields = messageString.split("\\|");
			
			if (messageFields.length == 3) {
				return new TangoServiceMessage(messageFields[0], messageFields[1], messageFields[2]);
			}
		}
		
		return new TangoServiceMessage(parameters.getDefaultStatus(), parameters.getDefaultMessage(), parameters.getDefaultGuid());
	}

	public TangoServiceMessage validateJsonSchema(String jsonRequest) {

		/*
		 * Validate using schema validator - on schema error return ERROR / Bad Request / Unprocessable Input 
		 */
		
		
		
		return buildMessage("PUT");
	}

}
