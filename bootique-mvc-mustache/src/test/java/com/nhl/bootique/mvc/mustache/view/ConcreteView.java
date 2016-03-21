package com.nhl.bootique.mvc.mustache.view;

import com.nhl.bootique.mvc.AbstractView;

// intentionally keeping the view class in a different package 
// from API and IT to test per-package template laoding
public class ConcreteView extends AbstractView {

	private Object model;

	public ConcreteView(String template, Object model) {
		super(template);
		this.model = model;
	}

	public Object getModel() {
		return model;
	}
}