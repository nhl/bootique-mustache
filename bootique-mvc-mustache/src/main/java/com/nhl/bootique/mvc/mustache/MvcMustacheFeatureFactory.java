package com.nhl.bootique.mvc.mustache;

public class MvcMustacheFeatureFactory {

	private String templateBase;

	public MvcMustacheFeatureFactory() {
		this.templateBase = "";
	}

	/**
	 * Sets a base location for Mustache templates. The value can be a file path
	 * or a classpath based URL. Templates paths are built using the following
	 * formula:
	 * 
	 * <pre>
	 * templateBase + calling_resource_package_and_classname + template_name.
	 * </pre>
	 * 
	 * The resulting path is first resolved as classpath, and if that fails - as
	 * a filesystem path.
	 * 
	 * @param templateBase
	 *            A base location for Mustache templates.
	 */
	// TODO: should we implement an alternative to Jersey crazy template loading
	// mechanism... E.g. dropwizard ties template paths to "view" objects (that
	// are really model objects)... FWIW with the current scheme Eclipse gives a
	// warning "Type Xyz collides with a package".
	public void setTemplateBase(String templateBase) {
		this.templateBase = templateBase;
	}

	public MvcMustacheFeature createMustacheFeature() {
		return new MvcMustacheFeature(templateBase);
	}
}
