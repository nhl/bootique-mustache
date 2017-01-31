package io.bootique.mvc.mustache;

import com.google.inject.Module;
import io.bootique.BQModule;
import io.bootique.BQModuleProvider;

public class MvcMustacheModuleProvider implements BQModuleProvider {

    @Override
    public Module module() {
        return new MvcMustacheModule();
    }

    @Override
    public BQModule.Builder moduleBuilder() {
        return BQModuleProvider.super
                .moduleBuilder()
                .description("Provides a renderer for bootique-mvc templates based on Mustache framework.");
    }
}
