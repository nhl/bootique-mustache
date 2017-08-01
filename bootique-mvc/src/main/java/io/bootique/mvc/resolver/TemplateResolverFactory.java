package io.bootique.mvc.resolver;

import io.bootique.annotation.BQConfig;
import io.bootique.annotation.BQConfigProperty;
import io.bootique.resource.FolderResourceFactory;

import java.nio.charset.Charset;
import java.util.Objects;

@BQConfig("Configures MVC template resolver.")
public class TemplateResolverFactory {

    private FolderResourceFactory templateBase;
    private Charset templateEncoding;
    private boolean templateLocationAbsolute;

    public TemplateResolverFactory() {
        this.templateBase = new FolderResourceFactory("");
        this.templateEncoding = Charset.forName("UTF-8");
        this.templateLocationAbsolute = false;
    }

    /**
     * Sets a base location of templates. Templates paths are built using the
     * following formula:
     * <p>
     * <pre>
     * template_path = templateBase + resource_package_path + template_name_with_extension
     * </pre>
     * <p>
     * Base defines how the template is resolved. It can be a URL, a special
     * "classpath:" URL, or a file path.
     *
     * @param templateBase A base location of templates.
     */
    @BQConfigProperty("Sets a base location of templates. Templates paths are built using the following formula: " +
            "\"template_path = templateBase + resource_package_path + template_name_with_extension\".")
    public void setTemplateBase(FolderResourceFactory templateBase) {
        this.templateBase = Objects.requireNonNull(templateBase);
    }

    /**
     * Sets template encoding. Default is UTF-8.
     *
     * @param templateEncoding expected encoding of templates.
     */
    @BQConfigProperty("Sets template encoding. Default is UTF-8.")
    public void setTemplateEncoding(String templateEncoding) {
        this.templateEncoding = Charset.forName(Objects.requireNonNull(templateEncoding));
    }

    /**
     * Indicates how to treat templateBase property. If the value is true then templates will be resolved relative to
     * the directory indicated by the templateBase property. Otherwise they will be resolved relatively to the view
     * class file.
     */
    @BQConfigProperty("Whether or not provided templateBase property should be treated as an absolute path.")
    public void setTemplateLocationAbsolute(boolean templateLocationAbsolute) {
        this.templateLocationAbsolute = templateLocationAbsolute;
    }

    public DefaultTemplateResolver createResolver() {
        return new DefaultTemplateResolver(templateBase, templateLocationAbsolute, templateEncoding);
    }
}
