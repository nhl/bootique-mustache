package io.bootique.mvc.freemarker;

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.bootique.ConfigModule;
import io.bootique.mvc.MvcModule;
import io.bootique.mvc.resolver.TemplateResolver;

import java.io.IOException;

public class MvcFreemarkerModule extends ConfigModule {

    @Override
    public void configure(Binder binder) {
        MvcModule.contributeRenderers(binder).addBinding(".ftl").to(FreemarkerTemplateRenderer.class);
    }

    @Provides
    @Singleton
    public FreemarkerIntegrationService createFreemarkerService(TemplateResolver resolver)
            throws IOException {
        return new FreemarkerIntegrationService(resolver);
    }
}
