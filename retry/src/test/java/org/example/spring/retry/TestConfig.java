package org.example.spring.retry;

import java.util.List;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.RetryListener;
import org.springframework.retry.stats.DefaultStatisticsRepository;
import org.springframework.retry.stats.StatisticsListener;
import org.springframework.retry.stats.StatisticsRepository;

@TestConfiguration
public class TestConfig {

	@Bean
	public StatisticsRepository statisticsRepository() {
		return new DefaultStatisticsRepository();
	}

	@Bean
	public RetryListener retryLister(StatisticsRepository statisticsRepository) {
		StatisticsListener listener = new StatisticsListener(
				statisticsRepository);
		return listener;
	}
	
	@Bean
	public List<RetryListener> retryListeners(RetryListener listener) {
		return List.of(listener);
	}

}