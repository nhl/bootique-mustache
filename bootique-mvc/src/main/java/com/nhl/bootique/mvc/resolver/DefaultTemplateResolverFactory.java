package com.nhl.bootique.mvc.resolver;

public class DefaultTemplateResolverFactory {

	static final String CLASSPATH_URL_PREFIX = "classpath:";

	private String templateBase;

	public DefaultTemplateResolverFactory() {
		this.templateBase = CLASSPATH_URL_PREFIX;
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

	public DefaultTemplateResolver createResolver() {
		return new DefaultTemplateResolver(templateBase);
	}
}
