package com.nhl.bootique.mvc.resolver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DefaultTemplateResolverTest {

	@Test
	public void testResourcePath_NullBase() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver(null);

		assertEquals("com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

	@Test
	public void testResourcePath_EmptyBase() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver("");

		assertEquals("com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

	@Test
	public void testResourcePath_FilePathBase() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver("/tmp");

		assertEquals("/tmp/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("/tmp/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

	@Test
	public void testResourcePath_FilePathBase_Slash() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver("/tmp/");

		assertEquals("/tmp/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("/tmp/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

	@Test
	public void testResourcePath_UrlBase() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver("http://example.org/a");

		assertEquals("http://example.org/a/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("http://example.org/a/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

	@Test
	public void testResourcePath_UrlBase_Slash() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver("http://example.org/a/");

		assertEquals("http://example.org/a/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("http://example.org/a/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

	@Test
	public void testResourcePath_ClasspathBase() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver("classpath:");

		assertEquals("classpath:com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("classpath:com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}
	
	@Test
	public void testResourcePath_ClasspathBase_Slash() {

		DefaultTemplateResolver resolver = new DefaultTemplateResolver("classpath:/");

		assertEquals("classpath:/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("tName.txt", DefaultTemplateResolverTest.class));
		assertEquals("classpath:/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourcePath("/tName.txt", DefaultTemplateResolverTest.class));
	}

}
