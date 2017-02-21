package io.bootique.mvc;

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.MapBinder;
import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import io.bootique.jersey.JerseyModule;
import io.bootique.mvc.renderer.ByExtensionTemplateRendererFactory;
import io.bootique.mvc.renderer.TemplateRenderer;
import io.bootique.mvc.renderer.TemplateRendererFactory;
import io.bootique.mvc.resolver.TemplateResolverFactory;
import io.bootique.mvc.resolver.TemplateResolver;

import java.util.Map;

public class MvcModule extends ConfigModule {

    /**
     * Returns an instance of {@link MvcModuleExtender} used by downstream modules to load custom extensions to the
     * MvcModule. Should be invoked from a downstream Module's "configure" method.
     *
     * @param binder DI binder passed to the Module that invokes this method.
     * @return an instance of {@link MvcModuleExtender} that can be used to load custom extensions to the MvcModule.
     * @since 0.6
     */
    public static MvcModuleExtender extend(Binder binder) {
        return new MvcModuleExtender(binder);
    }

    /**
     * @param binder DI binder passed to the Module that invokes this method.
     * @return returns a {@link MapBinder} for contributed template renderers.
     * @deprecated since 0.14 use {@link #extend(Binder)} to get an extender object, and
     * then call {@link MvcModuleExtender#setRenderer(String, Class)} or similar.
     */
    @Deprecated
    public static MapBinder<String, TemplateRenderer> contributeRenderers(Binder binder) {
        return MapBinder.newMapBinder(binder, String.class, TemplateRenderer.class);
    }

    @Override
    public void configure(Binder binder) {
        JerseyModule.extend(binder).addFeature(MvcFeature.class);
        MvcModule.extend(binder).initAllExtensions();
    }

    @Singleton
    @Provides
    MvcFeature createMvcFeature(TemplateResolver templateResolver, TemplateRendererFactory templateRendererFactory) {
        return new MvcFeature(templateResolver, templateRendererFactory);
    }

    @Singleton
    @Provides
    TemplateRendererFactory createTemplateRendererFactory(Map<String, TemplateRenderer> renderersByExtension) {
        return new ByExtensionTemplateRendererFactory(renderersByExtension);
    }

    @Singleton
    @Provides
    TemplateResolver createTemplateResolver(ConfigurationFactory configurationFactory) {
        return configurationFactory.config(TemplateResolverFactory.class, configPrefix).createResolver();
    }
}
