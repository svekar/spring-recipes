#  How to use simple scheduling (`@Scheduled`) in Spring Boot

## Problem
You need simple, stateless scheduling of tasks. You don't (yet)  need 
distributed scheduling like Quarts.

## Solution

Use the `@Scheduled` annotation in Spring to schedule method invocation.

1.  Annotate a procedure with `@Scheduled`. Use the attributes `cron` or `*String`
to make the schedule configurable instead of hardcoded.

1. Activate scheduling with `@EnableScheduling` on a configuration class 
(`@Configuration`).


## See also
* https://spring.io/guides/gs/scheduling-tasks/