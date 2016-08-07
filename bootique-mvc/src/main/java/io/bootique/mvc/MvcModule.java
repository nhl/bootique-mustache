package io.bootique.mvc;

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.MapBinder;
import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import io.bootique.jersey.JerseyModule;
import io.bootique.mvc.renderer.ByExtensionTemplateRendererFactory;
import io.bootique.mvc.renderer.TemplateRenderer;
import io.bootique.mvc.renderer.TemplateRendererFactory;
import io.bootique.mvc.resolver.DefaultTemplateResolverFactory;
import io.bootique.mvc.resolver.TemplateResolver;

import java.util.Map;

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
