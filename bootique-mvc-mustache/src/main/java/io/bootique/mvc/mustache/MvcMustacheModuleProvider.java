package io.bootique.mvc.mustache;

import com.google.inject.Module;
import io.bootique.BQModuleProvider;

public class MvcMustacheModuleProvider implements BQModuleProvider {

	@Override
	public Module module() {
		return new MvcMustacheModule();
	}
}
