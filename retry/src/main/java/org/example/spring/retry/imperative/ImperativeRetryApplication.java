package org.example.spring.retry.imperative;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.support.RetryTemplate;

@SpringBootApplication(proxyBeanMethods = false)
public class ImperativeRetryApplication implements CommandLineRunner {

	private final ImperativelyRetryableService retryableService;

	public ImperativeRetryApplication(@Value("${max.attempts}") int maxAttempts,
			@Autowired(required = true) List<RetryListener> listeners) {
		this.retryableService = new ImperativelyRetryableService(maxAttempts,
				listeners == null ? List.of() : listeners);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			retryableService.service();
		} catch (IllegalStateException e) {
			System.out.printf("Retryable service failed: %s", e);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ImperativeRetryApplication.class, args);
	}

}

class ImperativelyRetryableService {

	private final RetryTemplate retryTemplate;

	ImperativelyRetryableService(int maxAttempts,
			List<RetryListener> listeners) {
		retryTemplate = RetryTemplate.builder()
				.maxAttempts(maxAttempts)
				.withListeners(listeners)
				.fixedBackoff(20)
				.build();
	}

	void service() {
		RetryCallback<Void, IllegalStateException> callback = (RetryContext ctx) -> {
			System.out.println("Trying imperatively...");
			// For testing, really. StatisticsListener needs a name for the context.
			ctx.setAttribute(RetryContext.NAME, "service()");
			throw new IllegalStateException(
					"Tryed imperatively, but failed.");
		};
		retryTemplate.execute(callback);
	}

}