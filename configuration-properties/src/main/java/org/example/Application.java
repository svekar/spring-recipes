package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ConfigurationPropertiesRecord.class, ConfigurationPropertiesClass.class})
public class Application implements CommandLineRunner {

	private final ConfigurationPropertiesRecord configFromRecord;
	private final ConfigurationPropertiesClass configFromClass;	
	
	public Application(ConfigurationPropertiesRecord configFromRecord, ConfigurationPropertiesClass configFromClass) {
		this.configFromRecord = configFromRecord;
		this.configFromClass = configFromClass;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(configFromRecord);
		System.out.println(configFromClass);		
	}
}
