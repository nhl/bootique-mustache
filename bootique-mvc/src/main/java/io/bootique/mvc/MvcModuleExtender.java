package io.bootique.mvc;

import com.google.inject.Binder;
import com.google.inject.Singleton;
import com.google.inject.multibindings.MapBinder;
import io.bootique.mvc.renderer.TemplateRenderer;

/**
 * @since 0.6
 */
public class MvcModuleExtender {

    private Binder binder;
    private MapBinder<String, TemplateRenderer> templateRenderers;

    MvcModuleExtender(Binder binder) {
        this.binder = binder;
    }

    MvcModuleExtender initAllExtensions() {
        contributeRenderers();
        return this;
    }

    public MvcModuleExtender setRenderer(String handledExtension, TemplateRenderer renderer) {
        contributeRenderers().addBinding(handledExtension).toInstance(renderer);
        return this;
    }

    public MvcModuleExtender setRenderer(String handledExtension, Class<? extends TemplateRenderer> rendererType) {
        // TODO: what does singleton scope means when adding to collection?
        contributeRenderers().addBinding(handledExtension).to(rendererType).in(Singleton.class);
        return this;
    }

    protected MapBinder<String, TemplateRenderer> contributeRenderers() {
        if (templateRenderers == null) {
            templateRenderers = MapBinder.newMapBinder(binder, String.class, TemplateRenderer.class);
        }
        return templateRenderers;
    }

}
