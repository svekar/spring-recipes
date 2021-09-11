package org.example.spring.retry.imperative;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;

class ImperativeRetryInPlainJava {

	private final RetryTemplate retryTemplate;

	ImperativeRetryInPlainJava() {
		retryTemplate = RetryTemplate.builder()
				.fixedBackoff(42)
				.maxAttempts(7)
				.build();
	}

	void service() {
		RetryCallback<Void, RuntimeException> callback = ctx -> {
			System.out.println("Not getting stuff done...");
			System.out.println(2 / 0);
			return null;
		};
		retryTemplate.execute(callback);
	}
	
	public static void main(String[] args) {	
		try {
			new ImperativeRetryInPlainJava().service();
		} catch (ArithmeticException e) {
			System.out.println("Retryable service failed with: " + e);
		}
	}	

}
