package gov.usps.eir9361.testutility.ipmismocka.controller;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gov.usps.eir9361.testutility.ipmismocka.models.ParametersBean;
import gov.usps.eir9361.testutility.ipmismocka.models.TangoServiceMessage;
import gov.usps.eir9361.testutility.ipmismocka.services.MockaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MockaController {
	
	private final MockaService  mockaService;
	
	private final MessageSource messageSource;
	
	private final ParametersBean parameters;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/enable")
	public void enable(@RequestBody String testType) {
				
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		parameters.setTestType(testType);
				
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping()
	public ResponseEntity<?> serviceGetRequest() {
		
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		if (parameters.getTestType() == null) {
			return new ResponseEntity<>(messageSource.getMessage("messages.servererror", null, null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		TangoServiceMessage message = mockaService.buildMessage("GET");
		
		parameters.setTestType(null);
		
		if (message.getStatus().equals("ERROR")) {
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("")
	public ResponseEntity<?> servicePutRequest(@RequestBody String jsonRequest) {
		
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		if (parameters.getTestType() == null) {
			return new ResponseEntity<>(messageSource.getMessage("messages.servererror", null, null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		TangoServiceMessage message = mockaService.validateJsonSchema(jsonRequest);
		
		parameters.setTestType(null);
		
		if (message.getStatus().equals("ERROR")) {
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(message, HttpStatus.OK);
		
	}
	
	
	
	

}
