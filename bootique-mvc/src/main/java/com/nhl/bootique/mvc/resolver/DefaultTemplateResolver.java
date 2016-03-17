package com.nhl.bootique.mvc.resolver;

import com.nhl.bootique.mvc.Template;

public class DefaultTemplateResolver implements TemplateResolver {

	private String templateBase;

	public DefaultTemplateResolver(String templateBase) {
		this.templateBase = templateBase;
	}

	@Override
	public Template resolve(String templateName) {
		throw new UnsupportedOperationException("TODO");
	}
}
