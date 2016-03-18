package com.nhl.bootique.mvc.mustache;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.nhl.bootique.mvc.Template;
import com.nhl.bootique.mvc.renderer.TemplateRenderer;

public class MustacheTemplateRenderer implements TemplateRenderer {

	private MustacheFactory mustacheFactory;

	public MustacheTemplateRenderer() {
		this.mustacheFactory = new DefaultMustacheFactory();
	}

	@Override
	public void render(Writer out, Template template, Object rootModel) throws IOException {

		// TODO: cache templates...

		Mustache mustache = compile(template);
		mustache.execute(out, rootModel).flush();
	}

	Mustache compile(Template template) {
		// presumably Mustache closes the reader on its own...
		Reader reader = template.reader();
		return mustacheFactory.compile(reader, template.getName());
	}
}
