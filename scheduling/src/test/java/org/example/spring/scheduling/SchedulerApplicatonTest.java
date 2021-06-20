package org.example.spring.scheduling;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.regex.Pattern;

import static org.awaitility.Awaitility.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest({"fixed.rate=1000"})
final class SchedulerApplicatonTest {

	private final long rate;

	SchedulerApplicatonTest(@Value("${fixed.rate}") String rate) {
		this.rate = Long.valueOf(rate);
	}

	@Test
	void canStartMain() {
		assertDoesNotThrow(() -> SchedulerApplicaton.main(new String[]{}));
	}

	@Test
	void scheduledAtFixedRateRunsAt() {
		long duration = 2000;
		PrintStream out = System.out;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			System.setOut(new PrintStream(baos));
			await().atMost(duration, SECONDS)
					.until(() -> fixedRateLogMatches(
							baos.toString()) == duration / rate);
		} finally {
			System.setErr(out);
		}
	}

	private static long fixedRateLogMatches(String log) {
		Pattern p = Pattern.compile("(?m)^Fixed rate execution at .+\\.$");
		return p.matcher(log).results().count();
	}

}
