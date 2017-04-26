package io.bootique.mvc.freemarker;

import com.google.inject.Module;
import io.bootique.BQModule;
import io.bootique.BQModuleProvider;

public class MvcFreemarkerModuleProvider implements BQModuleProvider {

	@Override
	public Module module() {
		return new MvcFreemarkerModule();
	}

	@Override
	public BQModule.Builder moduleBuilder() {
		return BQModuleProvider.super.moduleBuilder().description("Provides a renderer for bootique-mvc templates based on Freemarker framework.");
	}
}
