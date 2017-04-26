package io.bootique.mvc.freemarker;

import com.google.inject.Inject;
import freemarker.cache.URLTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import io.bootique.mvc.resolver.TemplateResolver;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Lukasz Bachman
 */
class FreemarkerIntegrationService {

    private Configuration cfg = new Configuration(Configuration.getVersion());

    private Map<String, URL> urlMapping = new HashMap<>();
    private final boolean readFromDirectory;

    @Inject
    public FreemarkerIntegrationService(TemplateResolver resolver)
            throws IOException {
        readFromDirectory = resolver.isTemplateLocationAbsolute();
        cfg.setTemplateLoader(new URLTemplateLoader() {
            @Override
            protected URL getURL(String name) {
                URL url = null;
                if (readFromDirectory) {
                    try {
                        url = resolver.getTemplateBase().getUrl(name);
                    } catch (IllegalArgumentException iae) {
                        // We are silently dropping this exception to allow freemarker to load other localized variants of the same template.
                    }
                } else {
                    url = urlMapping.get(name);
                }
                return url;
            }
        });
        cfg.setDefaultEncoding(resolver.getTemplateEncoding().name());
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
    }

    Template getTemplate(String name, URL templateUrl)
            throws IOException {
        if (!readFromDirectory) {
            urlMapping.putIfAbsent(name, templateUrl);
        }
        return cfg.getTemplate(name);
    }
}
