package gov.usps.eir9361.testutility.ipmismocka.models;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ParametersBean {
	
	private String testType = null;
	
	@Value("${server.servlet.context-path}")
	private String contextPath;
	
	@Value("${app.default-return-status}")
	private String defaultStatus;
	
	@Value("${app.default-return-message}")
	private String defaultMessage;
	
	@Value("${app.default-return-guid}")
	private String defaultGuid;
	
	@Value("${app.api-test-type-file-path}")
	private String mapValuesFilePath;
	
	@Value("${app.validation-schema-file-path}")
	private String validationSchemaFilePath;
	
	private String validationSchema;
	
	private Map<String, String> apiTypeMap;
	
	

	

}
