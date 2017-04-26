package io.bootique.mvc.freemarker;

import com.google.inject.Inject;
import freemarker.template.TemplateException;
import io.bootique.mvc.Template;
import io.bootique.mvc.renderer.TemplateRenderer;

import java.io.IOException;
import java.io.Writer;

public class FreemarkerTemplateRenderer implements TemplateRenderer {

	private FreemarkerIntegrationService freemarkerIntegration;

	@Inject
	public FreemarkerTemplateRenderer(FreemarkerIntegrationService freemarkerIntegration)
			  throws IOException
	{
		this.freemarkerIntegration = freemarkerIntegration;
	}

	@Override
	public void render(Writer out, Template template, Object rootModel)
			  throws IOException
	{
		freemarker.template.Template freemarkerTemplate = freemarkerIntegration.getTemplate(template.getName(), template.getUrl());
		try {
			freemarkerTemplate.process(rootModel, out);
		}
		catch (TemplateException ex) {
			throw new IOException(String.format("Unexpected exception while processing template: %s", template.getUrl().getPath()), ex);
		}
	}
}
