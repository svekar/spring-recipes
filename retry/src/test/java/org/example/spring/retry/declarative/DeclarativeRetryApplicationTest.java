package org.example.spring.retry.declarative;

import static org.junit.jupiter.api.Assertions.*;

import org.example.spring.retry.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.retry.RetryStatistics;
import org.springframework.retry.stats.StatisticsRepository;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = {
		TestConfig.class}, properties = {"spring.main.banner-mode=off"})
class DeclarativeRetryApplicationTest {

	@Autowired
	private StatisticsRepository statisticsRepository;

	@Test
	void retryableServiceGetsRetriedThrougCommandLineRunner() {
		RetryStatistics stats = statisticsRepository.findAll()
				.iterator()
				.next();
		assertEquals(3, stats.getStartedCount());
	}

	@Test
	void canRunMain() {
		assertDoesNotThrow(
				() -> DeclarativeRetryApplication.main(new String[]{}));
	}

}
