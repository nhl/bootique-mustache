package com.nhl.bootique.mvc.renderer;

import java.io.Writer;

import com.nhl.bootique.mvc.Template;

public interface TemplateRenderer {

	void render(Writer out, Template template, Object rootModel);
}
