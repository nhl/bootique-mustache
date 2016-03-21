package com.nhl.bootique.mvc.resolver;

import static java.util.Arrays.asList;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Objects;

import com.nhl.bootique.mvc.Template;

public class DefaultTemplateResolver implements TemplateResolver {

	private Charset templateEncoding;
	private String templateBase;
	private TemplateLocator templateLocator;

	public DefaultTemplateResolver(String templateBase, Charset templateEncoding) {
		templateBase = normalizeTemplateBase(templateBase);
		this.templateBase = normalizeTemplateBase(templateBase);
		this.templateLocator = createTemplateLocator(templateBase);
		this.templateEncoding = Objects.requireNonNull(templateEncoding, "Null templateEncoding");
	}

	@Override
	public Template resolve(String templateName, Class<?> viewType) {

		String path = resourcePath(templateName, viewType);

		// template is a lazy, no need to cache it... Template rendering
		// providers should probably take care of caching of precompiled
		// templates
		return new Template() {

			@Override
			public URL getUrl() {
				return templateLocator.templateUrl(path);
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

	protected String resourcePath(String templateName, Class<?> viewType) {

		// path = templateBase + viewPackagePath + templateNameWithExt

		if (templateName.startsWith("/")) {
			templateName = templateName.substring(1);
		}

		Package pack = viewType.getPackage();
		String packagePath = pack != null ? pack.getName().replace('.', '/') + "/" : "";

		StringBuilder path = new StringBuilder(templateBase);

		path.append(packagePath);
		path.append(templateName);

		return path.toString();
	}

	protected String normalizeTemplateBase(String templateBase) {

		if (templateBase == null || templateBase.isEmpty()) {
			return "";
		}

		// no slash after classpath: is needed
		if (templateBase.equals(DefaultTemplateResolverFactory.CLASSPATH_URL_PREFIX)) {
			return templateBase;
		}

		// the rest of the base forms must end with slash
		if (templateBase.endsWith("/")) {
			return templateBase;
		}

		return templateBase + "/";
	}

	protected TemplateLocator createTemplateLocator(String templateBase) {
		if (templateBase.startsWith(DefaultTemplateResolverFactory.CLASSPATH_URL_PREFIX)) {
			return new ClasspathTemplateLocator();
		}

		int colon = templateBase.indexOf(':');
		if (colon > 0) {
			String scheme = templateBase.substring(0, colon);

			// TODO: dynamically detect available schemes?
			if (asList("http", "https", "file").contains(scheme)) {
				return new UrlTemplateLocator();
			}
		}

		return new FileTemplateLocator();
	}

	static interface TemplateLocator {
		URL templateUrl(String path);
	}

	static class ClasspathTemplateLocator implements TemplateLocator {

		@Override
		public URL templateUrl(String path) {
			String sansProtocol = path.substring(DefaultTemplateResolverFactory.CLASSPATH_URL_PREFIX.length());
			URL url = getClass().getClassLoader().getResource(sansProtocol);
			if (url == null) {
				throw new IllegalArgumentException("Not found on classpath: " + path);
			}

			return url;
		}
	}

	static class UrlTemplateLocator implements TemplateLocator {

		@Override
		public URL templateUrl(String path) {
			try {
				return new URL(path);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException("Not a valid URL: " + path, e);
			}
		}
	}

	static class FileTemplateLocator implements TemplateLocator {

		@Override
		public URL templateUrl(String path) {
			try {
				return new File(path).toURI().toURL();
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException("Not a valid file: " + path, e);
			}
		}
	}
}
