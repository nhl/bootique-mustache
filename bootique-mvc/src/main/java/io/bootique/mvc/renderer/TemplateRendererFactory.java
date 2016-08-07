package io.bootique.mvc.renderer;

import io.bootique.mvc.Template;

public interface TemplateRendererFactory {

	TemplateRenderer getRenderer(Template template);
}
