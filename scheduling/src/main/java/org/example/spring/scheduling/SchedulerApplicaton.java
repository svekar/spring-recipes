package org.example.spring.scheduling;

import java.time.Instant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication()
@EnableScheduling
public class SchedulerApplicaton {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplicaton.class, args);
	}

	@Scheduled(fixedRateString = "${fixed.rate}")
	void scheduledWithfixedRate() {
		System.out.printf("Fixed rate execution at %s.%n", Instant.now());
	}
	
	@Scheduled(cron = "${cron.schedule}")
	void scheduleFromCronExpression() {
		System.out.printf("Cron expression execution at %s.%n", Instant.now());
	}
}
