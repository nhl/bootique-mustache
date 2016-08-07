package io.bootique.mvc.renderer;

import java.io.IOException;
import java.io.Writer;

import io.bootique.mvc.Template;

public interface TemplateRenderer {

	void render(Writer out, Template template, Object rootModel) throws IOException;
}
