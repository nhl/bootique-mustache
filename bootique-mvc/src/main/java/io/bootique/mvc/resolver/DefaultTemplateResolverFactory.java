package io.bootique.mvc.resolver;

import io.bootique.resource.FolderResourceFactory;

import java.nio.charset.Charset;
import java.util.Objects;

public class DefaultTemplateResolverFactory {

	private FolderResourceFactory templateBase;
	private Charset templateEncoding;

	public DefaultTemplateResolverFactory() {
		this.templateBase = new FolderResourceFactory("");
		this.templateEncoding = Charset.forName("UTF-8");
	}

	/**
	 * Sets a base location of templates. Templates paths are built using the
	 * following formula:
	 * 
	 * <pre>
	 * template_path = templateBase + resource_package_path + template_name_with_extension
	 * </pre>
	 * 
	 * Base defines how the template is resolved. It can be a URL, a special
	 * "classpath:" URL, or a file path.
	 * 
	 * @param templateBase
	 *            A base location of templates.
	 */
	public void setTemplateBase(FolderResourceFactory templateBase) {
		this.templateBase = Objects.requireNonNull(templateBase);
	}

	/**
	 * Sets template encoding. Default is UTF-8.
	 * 
	 * @param templateEncoding
	 *            expected encoding of templates.
	 */
	public void setTemplateEncoding(String templateEncoding) {
		this.templateEncoding = Charset.forName(Objects.requireNonNull(templateEncoding));
	}

	public DefaultTemplateResolver createResolver() {
		return new DefaultTemplateResolver(templateBase, templateEncoding);
	}
}
