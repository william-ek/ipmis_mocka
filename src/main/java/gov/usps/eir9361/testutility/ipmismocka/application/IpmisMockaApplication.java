package gov.usps.eir9361.testutility.ipmismocka.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "gov.usps.eir9361.testutility.ipmismocka")
@SpringBootApplication
public class IpmisMockaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpmisMockaApplication.class, args);
	}

}
