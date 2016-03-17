package com.nhl.bootique.mvc;

import com.google.inject.Module;
import com.nhl.bootique.BQModuleProvider;

public class MvcModuleProvider implements BQModuleProvider {

	@Override
	public Module module() {
		return new MvcModule();
	}

}
