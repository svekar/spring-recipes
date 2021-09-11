package org.example.spring.retry.imperative;

import static org.junit.jupiter.api.Assertions.*;

import org.example.spring.retry.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.retry.RetryStatistics;
import org.springframework.retry.stats.StatisticsRepository;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = {
		TestConfig.class, ImperativeRetryApplication.class})
class ImperativeRetryApplicationTest {

	@Autowired
	private StatisticsRepository statisticsRepository;

	@Test
	void test() {
		RetryStatistics stats = statisticsRepository.findAll()
				.iterator()
				.next();
		assertEquals(3, stats.getStartedCount());
	}

	@Test
	void canRunMain() {
		assertDoesNotThrow(
				() -> ImperativeRetryApplication.main(new String[]{}));
	}
}
