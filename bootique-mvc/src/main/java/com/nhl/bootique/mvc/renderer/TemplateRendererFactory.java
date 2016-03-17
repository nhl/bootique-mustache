package com.nhl.bootique.mvc.renderer;

import com.nhl.bootique.mvc.Template;

public interface TemplateRendererFactory {

	TemplateRenderer getRenderer(Template template);
}
