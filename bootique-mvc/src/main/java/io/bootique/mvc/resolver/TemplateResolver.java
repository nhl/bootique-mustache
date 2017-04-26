package io.bootique.mvc.resolver;

import io.bootique.mvc.Template;
import io.bootique.resource.FolderResourceFactory;

import java.nio.charset.Charset;

public interface TemplateResolver {

	Template resolve(String templateName, Class<?> viewType);

	Charset getTemplateEncoding();

	FolderResourceFactory getTemplateBase();

	boolean isTemplateLocationAbsolute();

}
