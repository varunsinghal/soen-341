package com.soen.empower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Empower is Spring Boot application. This makes it easy to create stand-alone production-grade
 * spring based applications. Key features:
 * <ul>
 * <li>Embedded Tomcat</li>
 * <li>No need to deploy WAR files</li>
 * <li>No XML configuration</li>
 * <li>Health checks using actuator and dev tools</li>
 * <li>Automatic dependency managment of Spring and 3rd party libraries.</li>
 * </ul>
 * <p>
 * The java doc comments are referenced using the guide present in the link below.
 * <a href="https://www.oracle.com/technetwork/java/javase/documentation/index-137868.html#format">CLick here.</a>
 * </p>
 *
 * @author Varun Singhal
 * @since 1.0
 */
@SpringBootApplication
public class EmpowerApplication {


	/**
	 * The method which launches the application with system arguments provided by the console.
	 *
	 * @param args system arguments, if required to launch the console application
	 */
	public static void main(String[] args) {
		SpringApplication.run(EmpowerApplication.class, args);
	}
}
