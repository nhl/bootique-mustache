package io.bootique.mvc;

import com.google.inject.Module;
import io.bootique.BQModule;
import io.bootique.BQModuleProvider;
import io.bootique.mvc.resolver.TemplateResolverFactory;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

public class MvcModuleProvider implements BQModuleProvider {

    @Override
    public Module module() {
        return new MvcModule();
    }


    @Override
    public Map<String, Type> configs() {
        // TODO: config prefix is hardcoded. Refactor away from ConfigModule, and make provider
        // generate config prefix, reusing it in metadata...
        return Collections.singletonMap("mvc", TemplateResolverFactory.class);
    }

    @Override
    public BQModule.Builder moduleBuilder() {
        return BQModuleProvider.super
                .moduleBuilder()
                .description("Provides a REST-based web MVC engine with pluggable view renderers.");
    }
}
