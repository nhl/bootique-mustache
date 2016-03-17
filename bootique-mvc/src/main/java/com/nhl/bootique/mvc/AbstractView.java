package com.nhl.bootique.mvc;

import java.nio.charset.Charset;

/**
 * A superclass of MVC views. It contains a name of the view template, that is
 * resolved outside of the view. AbstractView (or rather its concrete subclass)
 * is used as a root context for resolving the template. So subclasses would
 * normally define getters that allow to access the underlying model objects.
 */
public abstract class AbstractView {

	private static final Charset UTF8 = Charset.forName("UTF-8");

	protected String templateName;
	protected Charset encoding;

	public AbstractView(String templateName) {
		this(templateName, UTF8);
	}

	public AbstractView(String templateName, Charset encoding) {
		this.templateName = templateName;
		this.encoding = encoding;
	}

	public String getTemplateName() {
		return templateName;
	}

	public Charset getEncoding() {
		return encoding;
	}
}
