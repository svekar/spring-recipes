package org.example.spring.retry.declarative;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@SpringBootApplication(proxyBeanMethods = false)
@EnableRetry
public class DeclarativeRetryApplication implements CommandLineRunner {

	private final RetryableService retryableService;

	public DeclarativeRetryApplication(RetryableService bean) {
		this.retryableService = bean;
	}

	public static void main(String[] args) {
		SpringApplication.run(DeclarativeRetryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			retryableService.service();
		} catch (IllegalStateException e) {
			System.out.printf("Retryable service failed: %s", e);
		}
	}

}

@Service
class RetryableService {

	public RetryableService() {

	}

	@Retryable(maxAttemptsExpression = "${max.attempts}", backoff = @Backoff(delay = 20))
	public void service() {
		System.out.println("Trying, but failing...");
		throw new IllegalStateException();
	}

}
