package org.example;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "")
record ConfigurationPropertiesRecord(My my) {

	record My(First first, Second second) {}
	record First(Setting setting) {}
	record Setting(int one, String two, boolean three) {}
	
	record Second(Setting setting) {}
}
