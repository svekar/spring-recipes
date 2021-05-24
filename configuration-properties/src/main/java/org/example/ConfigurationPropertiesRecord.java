package org.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "")
@ConstructorBinding
record ConfigurationPropertiesRecord(My my) {

	record My(First first, Second second) {}
	record First(Setting setting) {}
	record Setting(int one, String two, boolean three) {}
	
	record Second(Setting setting) {}
}
