package com.nhl.bootique.mvc.mustache;

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.nhl.bootique.ConfigModule;
import com.nhl.bootique.config.ConfigurationFactory;
import com.nhl.bootique.jersey.JerseyModule;

public class MvcMustacheModule extends ConfigModule {

	@Override
	public void configure(Binder binder) {
		JerseyModule.contributeFeatures(binder).addBinding().to(MvcMustacheFeature.class);
	}

	@Singleton
	@Provides
	MvcMustacheFeature createMustanceFeature(ConfigurationFactory configurationFactory) {
		return configurationFactory.config(MvcMustacheFeatureFactory.class, configPrefix).createMustacheFeature();
	}
}
