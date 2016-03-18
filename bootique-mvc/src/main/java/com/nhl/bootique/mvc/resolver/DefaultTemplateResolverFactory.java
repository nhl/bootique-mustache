package com.nhl.bootique.mvc.resolver;

import java.nio.charset.Charset;
import java.util.Objects;

public class DefaultTemplateResolverFactory {

	static final String CLASSPATH_URL_PREFIX = "classpath:";

	private String templateBase;
	private Charset templateEncoding;

	public DefaultTemplateResolverFactory() {
		this.templateBase = CLASSPATH_URL_PREFIX;
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
	public void setTemplateBase(String templateBase) {
		this.templateBase = templateBase != null ? templateBase : "";
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
