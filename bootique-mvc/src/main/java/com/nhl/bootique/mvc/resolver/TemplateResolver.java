package com.nhl.bootique.mvc.resolver;

import com.nhl.bootique.mvc.Template;

public interface TemplateResolver {

	Template resolve(String templateName, Class<?> viewType);
}
