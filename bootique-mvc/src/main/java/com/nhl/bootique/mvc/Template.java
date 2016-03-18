package com.nhl.bootique.mvc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public interface Template {

	String getName();

	Charset getEncoding();

	URL getUrl();

	default Reader reader() {
		Charset encoding = getEncoding();
		URL url = getUrl();
		try {
			return new InputStreamReader(url.openStream(), encoding);
		} catch (IOException e) {
			throw new RuntimeException("Error opening URL: " + url, e);
		}
	}
}
