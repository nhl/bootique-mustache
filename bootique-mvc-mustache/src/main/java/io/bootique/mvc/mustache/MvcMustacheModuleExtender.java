package io.bootique.mvc.mustache;

import com.google.inject.Binder;
import io.bootique.ModuleExtender;

import java.util.concurrent.ExecutorService;

/**
 * @since 1.0
 */
public class MvcMustacheModuleExtender extends ModuleExtender<MvcMustacheModuleExtender> {
    MvcMustacheModuleExtender(Binder binder) {
        super(binder);
    }

    @Override
    public MvcMustacheModuleExtender initAllExtensions() {
        return this;
    }

    public MvcMustacheModuleExtender setRenderer(ExecutorService es) {
        binder.bind(ExecutorService.class).toInstance(es);
        return this;
    }
}
