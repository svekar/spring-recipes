package org.example.spring.retry.imperative;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ImperativeRetryInPlainJavaTest {

	@Test
	void canRunMain() {
		assertDoesNotThrow(() -> ImperativeRetryInPlainJava.main(new String[] {}));
	}

}
