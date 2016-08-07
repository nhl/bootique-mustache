package io.bootique.mvc.resolver;

import io.bootique.mvc.Template;

public interface TemplateResolver {

	Template resolve(String templateName, Class<?> viewType);
}
