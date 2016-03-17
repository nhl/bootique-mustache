package com.nhl.bootique.mvc.mustache;

import com.google.inject.Module;
import com.nhl.bootique.BQModuleProvider;

public class MvcMustacheModuleProvider implements BQModuleProvider {

	@Override
	public Module module() {
		return new MvcMustacheModule();
	}
}
