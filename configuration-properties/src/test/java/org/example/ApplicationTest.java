package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ApplicationTest {

	@Test
	void canRunMain() {
		assertDoesNotThrow(() -> Application.main(new String[]{}));
	}

}
