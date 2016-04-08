package com.nhl.bootique.mvc.resolver;

import static org.junit.Assert.assertEquals;

import java.nio.charset.Charset;

import org.junit.Test;

import com.nhl.bootique.resource.ResourceFactory;

public class DefaultTemplateResolverTest {

	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private DefaultTemplateResolver resolver(String basePath) {
		return new DefaultTemplateResolver(new ResourceFactory(basePath), DEFAULT_CHARSET);
	}

	private String baseCPAsFileUrl() {
		// TODO: windows
		return baseFileUrl() + "target/test-classes/";
	}

	private String baseFileUrl() {
		// TODO: windows
		return "file:" + System.getProperty("user.dir") + "/";
	}

	@Test
	public void testResourcePath_EmptyBase() {

		DefaultTemplateResolver resolver = resolver("");

		assertEquals(baseFileUrl() + "com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
		assertEquals(baseFileUrl() + "com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
	}

	@Test
	public void testResourcePath_FilePathBase() {

		DefaultTemplateResolver resolver = resolver("/tmp");

		assertEquals("file:/tmp/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
		assertEquals("file:/tmp/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
	}

	@Test
	public void testResourcePath_FilePathBase_Slash() {

		DefaultTemplateResolver resolver = resolver("/tmp/");

		assertEquals("file:/tmp/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
		assertEquals("file:/tmp/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
	}

	@Test
	public void testResourcePath_UrlBase() {

		DefaultTemplateResolver resolver = resolver("http://example.org/a");

		assertEquals("http://example.org/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
		assertEquals("http://example.org/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
	}

	@Test
	public void testResourcePath_UrlBase_Slash() {

		DefaultTemplateResolver resolver = resolver("http://example.org/a/");

		assertEquals("http://example.org/a/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
		assertEquals("http://example.org/a/com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
	}

	@Test
	public void testResourcePath_ClasspathBase() {

		DefaultTemplateResolver resolver = resolver("classpath:");

		assertEquals(baseCPAsFileUrl() + "com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
		assertEquals(baseCPAsFileUrl() + "com/nhl/bootique/mvc/resolver/tName.txt",
				resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
	}

	@Test(expected = RuntimeException.class)
	public void testResourcePath_ClasspathBase_Slash() {
		DefaultTemplateResolver resolver = resolver("classpath:/");
		resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class);
	}

}
