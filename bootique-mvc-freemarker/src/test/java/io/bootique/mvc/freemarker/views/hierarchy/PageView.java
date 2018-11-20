package io.bootique.mvc.freemarker.views.hierarchy;

import io.bootique.mvc.AbstractView;

import java.util.Map;

/**
 * @author Lukasz Bachman
 */
public class PageView extends AbstractView {

	private final Map<String, Object> model;

	public PageView(String templateName, Map<String, Object> model) {
		super(templateName);
		this.model = model;
	}

}
