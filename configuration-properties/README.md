# How to use Spring Boot's `@ConfigurationProperties`

## Problem
You want to get some or all of your application settings mapped into a
type-safe Java class, probably because the alternatives are cumbersome 
and leads to ill-designed code (repeated Environment.getProperty() invocations 
or `@Value` annotations littered throughout the code).

## Solution

Use [@ConfigurationProperties](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.external-config.typesafe-configuration-properties) to have Spring Boot automatically 
populate POJOs or even Java 16 records with your configuration, then inject the 
root class of your configuration.

1. Create a class or record annotated with `@ConfigurationProperties(prefix = "")`.
Design subclasses for each hierarchical part of the properties naming scheme. 
Use a more precise prefix if you want to match parts of a hierarchical property 
naming schemes. Examples:
    * [Record](./src/main/java/org/example/ConfigurationPropertiesRecord.java)
    * [Old-style class](./src/main/java/org/example/ConfigurationPropertiesClass.java)  

2. Add `@ConstructorBinding` to the root class if you want the class to be 
immutable. This instructs Spring Boot to inject values through your constructors.
Otherwise, proceed with setter methods.

3. Enable Configuration Properties on the main Spring Boot configuration
Add `@EnableConfigurationProperties` to the configuration class, e.g.
your [@SpringBootApplication](src/main/java/org/example/Application.java). 
Add each of your configuration properties classes to the value of this annotation. 

4. Inject your configuration properties classes.
