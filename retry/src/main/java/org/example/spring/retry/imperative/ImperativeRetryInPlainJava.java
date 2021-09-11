package org.example.spring.retry.imperative;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;

class ImperativeRetryInPlainJava {

	private final RetryTemplate retryTemplate;

	private final RetryCallback<Integer, RuntimeException> callback = ctx -> {
		System.out.println("Not getting stuff done...");
		return 42 / 0;
	};
	
	ImperativeRetryInPlainJava() {
		retryTemplate = RetryTemplate.builder()
				.fixedBackoff(42)
				.maxAttempts(7)
				.build();
	}

	int service() {
		return retryTemplate.execute(callback);
	}
	
	public static void main(String[] args) {	
		try {
			new ImperativeRetryInPlainJava().service();
		} catch (ArithmeticException e) {
			System.out.println("Retryable service failed with: " + e);
		}
	}	

}
