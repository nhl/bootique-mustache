package io.bootique.mvc;

import com.google.inject.Module;
import io.bootique.BQModuleProvider;

public class MvcModuleProvider implements BQModuleProvider {

	@Override
	public Module module() {
		return new MvcModule();
	}

}
