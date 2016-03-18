package com.nhl.bootique.mvc.resolver;

import javax.ws.rs.container.ResourceInfo;

import com.nhl.bootique.mvc.Template;

public interface TemplateResolver {

	Template resolve(String templateName, ResourceInfo resourceInfo);
}
