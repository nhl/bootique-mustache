package com.nhl.bootique.mvc.mustache;

import com.google.inject.Binder;
import com.nhl.bootique.ConfigModule;
import com.nhl.bootique.mvc.MvcModule;

public class MvcMustacheModule extends ConfigModule {

	@Override
	public void configure(Binder binder) {
		MvcModule.contributeRenderers(binder).addBinding(".mustache").to(MustacheTemplateRenderer.class);
	}

}
