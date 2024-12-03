package gov.usps.eir9361.testutility.ipmismocka.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TangoServiceMessage {
	
	public TangoServiceMessage(String defaultStatus, String defaultMessage, String defaultGuid) {
		this.status = defaultStatus;
		this.status_message = defaultMessage;
		this.gu_id = defaultGuid;
	}

	private String status;
	
	private String status_message;
	
	private String gu_id;

}
