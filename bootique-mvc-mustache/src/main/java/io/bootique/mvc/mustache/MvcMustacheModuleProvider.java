package io.bootique.mvc.mustache;

import com.google.inject.Module;
import io.bootique.BQModule;
import io.bootique.BQModuleProvider;
import io.bootique.jersey.JerseyModuleProvider;
import io.bootique.mvc.MvcModuleProvider;

import java.util.Collection;

import static java.util.Arrays.asList;

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

    @Override
    public Collection<BQModuleProvider> dependencies() {
        return asList(
                new MvcModuleProvider(),
                new JerseyModuleProvider()
        );
    }
}
