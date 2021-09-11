package org.example.spring.retry.declarative;

import static org.junit.jupiter.api.Assertions.*;

import org.example.spring.retry.TestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.retry.RetryStatistics;
import org.springframework.retry.stats.StatisticsRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DeclarativeRetryApplication.class,
		TestConfig.class}, initializers = ConfigDataApplicationContextInitializer.class)
@TestPropertySource(properties = "max.attempts=3")
class RetryableServiceTest {

	@Autowired
	private RetryableService retryableService;

	@Autowired
	private StatisticsRepository statisticsRepository;

	@Test
	void retryableServiceGetsRetried() {
		assertThrows(IllegalStateException.class,
				() -> retryableService.service());
		RetryStatistics stats = statisticsRepository.findAll()
				.iterator()
				.next();
		assertEquals(3, stats.getStartedCount());
	}

}
