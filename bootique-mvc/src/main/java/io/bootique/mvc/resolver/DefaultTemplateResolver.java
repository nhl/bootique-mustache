package io.bootique.mvc.resolver;

import io.bootique.mvc.Template;
import io.bootique.resource.FolderResourceFactory;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Objects;

public class DefaultTemplateResolver implements TemplateResolver {

    private Charset templateEncoding;
    private FolderResourceFactory templateBase;
    private boolean templateLocationAbsolute;

    public DefaultTemplateResolver(FolderResourceFactory templateBase, boolean templateLocationAbsolute, Charset templateEncoding) {
        this.templateBase = templateBase;
        this.templateLocationAbsolute = templateLocationAbsolute;
        this.templateEncoding = Objects.requireNonNull(templateEncoding, "Null templateEncoding");
    }

    @Override
    public Template resolve(String templateName, Class<?> viewType) {

        // template is lazy, no need to cache it... Template rendering
        // providers should probably take care of caching of precompiled
        // templates
        return new Template() {

            @Override
            public URL getUrl() {
                return resourceUrl(templateName, viewType);
            }

            @Override
            public Charset getEncoding() {
                return templateEncoding;
            }

            @Override
            public String getName() {
                return templateName;
            }
        };
    }

    @Override
    public Charset getTemplateEncoding() {
        return templateEncoding;
    }

    @Override
    public FolderResourceFactory getTemplateBase() {
        return templateBase;
    }

    @Override
    public boolean isTemplateLocationAbsolute() {
        return templateLocationAbsolute;
    }

    protected URL resourceUrl(String templateName, Class<?> viewType) {
        String path = templateLocationAbsolute ? templateName : relativeResourcePath(templateName, viewType);
        return templateBase.getUrl(path);
    }

    protected String relativeResourcePath(String templateName, Class<?> viewType) {

        // path = viewPackagePath + templateNameWithExt

        if (templateName.startsWith("/")) {
            templateName = templateName.substring(1);
        }

        Package pack = viewType.getPackage();
        String packagePath = pack != null ? pack.getName().replace('.', '/') + "/" : "";

        StringBuilder path = new StringBuilder();

        path.append(packagePath);
        path.append(templateName);

        return path.toString();
    }
}
