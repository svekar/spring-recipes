# How to use Spring Retry in Spring Boot (and plain Java)

## The problem
You want retries around invocations of faulty third party services.


## Solution
There are several retry libraries out there, but this time we will try to solve 
the problem with Spring Retry. The library offers two approaches to configuring
retries, a declarative (i.e. annotation-driven) and an imperative (programmatic) 
one.

### Declarative way

1. Add the following [dependencies](./pom.xml) to your project:
 
    ```xml
    <dependency>
     <groupId>org.springframework.retry</groupId>
     <artifactId>spring-retry</artifactId>
    </dependency>
    <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
    ```
 
 The second one is needed to Spring AOP to work.
    
2. Add the annotation `@EnableRetry` to one of your [configuration](./src/main/java/org/example/spring/retry/declarative/DeclarativeRetryApplication.java) classes, e.g:
 
    ```java
    @SpringBootApplication(proxyBeanMethods = false)
    @EnableRetry
    public class DeclarativeRetryApplication implements CommandLineRunner {
    ...
    ```
 
3. Add the annotation `@Retryable` to a bean method you want retries on, e.g:
 
    ```java
    @Service
    class RetryableService {
    ...
	   @Retryable(maxAttemptsExpression = "${max.attempts}")
	   public void service() {
	   ...
    ```
 
 I have so far found that both beans annotated with `@Service` beans 
registered with an `@Bean`-annotated method in a `@Configuration` class, works.

4. Configure `Retryable` to your liking. The factory default starts out with
 a maximal retries attempt of 3 and 1000 ms between attempts.


### Imperative way
1. Add just the `spring-retry` dependency above to your [POM](./pom.xml). The AOP 
dependencies are not needed in the programmatic configuration.

1. Define a [`RetryTemplate`](./src/main/java/org/example/spring/retry/imperative/ImperativeRetryApplication.java), e.g:
 
    ```java
   		RetryTemplate retryTemplate = RetryTemplate.builder()
   		   ...
								.build();
    ```
 
1. Use it around method invocations you want retried, e.g:
 
    ```java
    ...
    void service() {
        RetryCallback<Void, IllegalStateException> callback = ctx -> {
            faultyThirdPartyService();
            return null
        };
        retryTemplate.execute(callback);
    }
    ```
 The callback syntax is easier when the method invoked returns a value. Then 
  you dont't need to deal with `void` in the lambda, and can often get by with 
  a concise one-liner lambda. 
  
 
1. Configure your retries by tweaking the `RetryTemplate`.

#### Plain java
It seems to be possible to use Spring Retry in plain Java, too. From plain Java 
the procedure is the same as the imperative one, above. See 
[ImperativeRetryInPlainJava](./src/main/java/org/example/spring/retry/imperative/ImperativeRetryInPlainJava.java) 
for an example.

## See also
* [Spring Retry](https://github.com/spring-projects/spring-retry) 