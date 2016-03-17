package com.nhl.bootique.mvc;

import java.util.Map;

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.MapBinder;
import com.nhl.bootique.ConfigModule;
import com.nhl.bootique.config.ConfigurationFactory;
import com.nhl.bootique.jersey.JerseyModule;
import com.nhl.bootique.mvc.renderer.ByExtensionTemplateRendererFactory;
import com.nhl.bootique.mvc.renderer.TemplateRenderer;
import com.nhl.bootique.mvc.renderer.TemplateRendererFactory;
import com.nhl.bootique.mvc.resolver.DefaultTemplateResolverFactory;
import com.nhl.bootique.mvc.resolver.TemplateResolver;

public class MvcModule extends ConfigModule {

	public static MapBinder<String, TemplateRenderer> contributeRenderers(Binder binder) {
		return MapBinder.newMapBinder(binder, String.class, TemplateRenderer.class);
	}

	@Override
	public void configure(Binder binder) {

		JerseyModule.contributeFeatures(binder).addBinding().to(MvcFeature.class);

		// bootstrap collections
		MvcModule.contributeRenderers(binder);
	}

	@Singleton
	@Provides
	MvcFeature createMvcFeature(TemplateResolver templateResolver, TemplateRendererFactory templateRendererFactory) {
		return new MvcFeature(templateResolver, templateRendererFactory);
	}

	@Singleton
	@Provides
	TemplateRendererFactory createTemplateRendererFactory(Map<String, TemplateRenderer> renderersByExtension) {
		return new ByExtensionTemplateRendererFactory(renderersByExtension);
	}

	@Singleton
	@Provides
	TemplateResolver createTemplateResolver(ConfigurationFactory configurationFactory) {
		return configurationFactory.config(DefaultTemplateResolverFactory.class, configPrefix).createResolver();
	}
}
