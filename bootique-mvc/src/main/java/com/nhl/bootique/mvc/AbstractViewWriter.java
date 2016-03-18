package com.nhl.bootique.mvc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Objects;

import javax.inject.Provider;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import com.nhl.bootique.mvc.renderer.TemplateRendererFactory;
import com.nhl.bootique.mvc.resolver.TemplateResolver;

public class AbstractViewWriter implements MessageBodyWriter<AbstractView> {

	private TemplateResolver templateResolver;
	private TemplateRendererFactory templateRendererFactory;

	// TODO dirty ... other properties are set in constructor, but this one
	// awaits injection. So the object is in a partial state for the part of its
	// lifecycle...
	@Context
	private Provider<ResourceInfo> resourceInfoProvider;

	public AbstractViewWriter(TemplateResolver templateResolver, TemplateRendererFactory templateRendererFactory) {
		this.templateResolver = templateResolver;
		this.templateRendererFactory = templateRendererFactory;
	}

	@Override
	public long getSize(AbstractView t, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return AbstractView.class.isAssignableFrom(type);
	}

	@Override
	public void writeTo(AbstractView t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
					throws IOException, WebApplicationException {

		// doublecheck injection....
		Objects.requireNonNull(resourceInfoProvider, "'resourceInfoProvider' was not injected");

		Writer out = new OutputStreamWriter(entityStream, t.getEncoding());
		Template template = templateResolver.resolve(t.getTemplateName(), resourceInfoProvider.get());
		templateRendererFactory.getRenderer(template).render(out, template, t);
		out.flush();
	}

}
