package io.bootique.mvc.freemarker.views;

import io.bootique.mvc.AbstractView;

import java.util.Objects;

/**
 * @author Lukasz Bachman
 */
public class HelloWorldView extends AbstractView {

	private final String firstName;
	private final String lastName;

	public HelloWorldView(String template, String firstName, String lastName) {
		super(template);
		this.firstName = Objects.requireNonNull(firstName);
		this.lastName = Objects.requireNonNull(lastName);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}