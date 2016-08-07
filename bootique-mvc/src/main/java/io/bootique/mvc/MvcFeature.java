package io.bootique.mvc;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import io.bootique.mvc.renderer.TemplateRendererFactory;
import io.bootique.mvc.resolver.TemplateResolver;

public class MvcFeature implements Feature {

	private TemplateResolver templateResolver;
	private TemplateRendererFactory templateRendererFactory;

	public MvcFeature(TemplateResolver templateResolver, TemplateRendererFactory templateRendererFactory) {
		this.templateResolver = templateResolver;
		this.templateRendererFactory = templateRendererFactory;
	}

	@Override
	public boolean configure(FeatureContext context) {
		context.register(new AbstractViewWriter(templateResolver, templateRendererFactory));
		return true;
	}
}
