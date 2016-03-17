package com.nhl.bootique.mvc.mustache;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.jersey.server.mvc.mustache.MustacheMvcFeature;

public class MvcMustacheFeature implements Feature {

	private String templateBase;

	public MvcMustacheFeature(String templateBase) {
		this.templateBase = templateBase;
	}

	@Override
	public boolean configure(FeatureContext context) {

		// set properties
		context.property(MustacheMvcFeature.TEMPLATE_BASE_PATH, templateBase);

		// contribute Jersey Mustache feature
		new MustacheMvcFeature().configure(context);

		return true;
	}

}
