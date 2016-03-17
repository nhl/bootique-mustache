package com.nhl.bootique.mvc.renderer;

import java.util.Map;

import com.nhl.bootique.mvc.Template;

public class ByExtensionTemplateRendererFactory implements TemplateRendererFactory {

	private Map<String, TemplateRenderer> renderersByExtension;

	public ByExtensionTemplateRendererFactory(Map<String, TemplateRenderer> renderersByExtension) {
		// expecting . to be present in extension names...
		this.renderersByExtension = renderersByExtension;
	}

	@Override
	public TemplateRenderer getRenderer(Template template) {
		String ext = getExtension(template.getPath());
		TemplateRenderer renderer = renderersByExtension.get(ext);
		if (renderer == null) {
			throw new IllegalArgumentException("Unsupported template extension: " + ext + ", supported extensions: "
					+ renderersByExtension.keySet());
		}

		return renderer;
	}

	String getExtension(String path) {
		int dot = path.lastIndexOf('.');
		if (dot <= 0 || dot == path.length() - 1) {
			// TODO: if we only have one renderer, perhaps no extension is fine?
			throw new IllegalArgumentException("Path without extension: " + path);
		}

		// include dot in the result
		return path.substring(dot);
	}
}
