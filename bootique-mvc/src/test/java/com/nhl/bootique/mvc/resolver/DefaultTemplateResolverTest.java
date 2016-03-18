package com.nhl.bootique.mvc.resolver;

import static org.junit.Assert.assertEquals;

import java.nio.charset.Charset;

import org.junit.Test;

public class DefaultTemplateResolverTest {

	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	@Test
	public void testResourcePath_NullBase() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver(null, DEFAULT_CHARSET);

		assertEquals("com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

	@Test
	public void testResourcePath_EmptyBase() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver("", DEFAULT_CHARSET);

		assertEquals("com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

	@Test
	public void testResourcePath_FilePathBase() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver("/tmp", DEFAULT_CHARSET);

		assertEquals("/tmp/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("/tmp/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

	@Test
	public void testResourcePath_FilePathBase_Slash() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver("/tmp/", DEFAULT_CHARSET);

		assertEquals("/tmp/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("/tmp/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

	@Test
	public void testResourcePath_UrlBase() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver("http://example.org/a", DEFAULT_CHARSET);

		assertEquals("http://example.org/a/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("http://example.org/a/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

	@Test
	public void testResourcePath_UrlBase_Slash() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver("http://example.org/a/", DEFAULT_CHARSET);

		assertEquals("http://example.org/a/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("http://example.org/a/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

	@Test
	public void testResourcePath_ClasspathBase() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver("classpath:", DEFAULT_CHARSET);

		assertEquals("classpath:com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("classpath:com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

	@Test
	public void testResourcePath_ClasspathBase_Slash() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver("classpath:/", DEFAULT_CHARSET);

		assertEquals("classpath:/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("classpath:/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

}
