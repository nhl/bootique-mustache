package io.bootique.mvc.resolver;

import io.bootique.resource.FolderResourceFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class DefaultTemplateResolverTest {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    @Test
    public void testResourcePath_EmptyBase()
            throws MalformedURLException
    {

        DefaultTemplateResolver resolver = resolver("");

        URL expected = baseUrl("io/bootique/mvc/resolver/tName.txt");
        assertEquals(expected, resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class));
        assertEquals(expected, resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class));
    }

    @Test
    public void testResourcePath_FilePathBase() throws IOException {

        DefaultTemplateResolver resolver = resolver("/tmp");

        File canonical = new File("/tmp/io/bootique/mvc/resolver/tName.txt").getCanonicalFile();

        assertEquals(canonical.toURI().toURL(),
                resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class));
        assertEquals(canonical.toURI().toURL(),
                resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class));
    }

    @Test
    public void testResourcePath_FilePathBase_Slash() throws IOException {

        DefaultTemplateResolver resolver = resolver("/tmp/");

        File canonical = new File("/tmp/io/bootique/mvc/resolver/tName.txt").getCanonicalFile();

        assertEquals(canonical.toURI().toURL(),
                resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class));
        assertEquals(canonical.toURI().toURL(),
                resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class));
    }

    @Test
    public void testResourcePath_UrlBase() {

        DefaultTemplateResolver resolver = resolver("http://example.org/a");

        assertEquals("http://example.org/a/io/bootique/mvc/resolver/tName.txt",
                resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
        assertEquals("http://example.org/a/io/bootique/mvc/resolver/tName.txt",
                resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
    }

    @Test
    public void testResourcePath_UrlBase_Slash() {

        DefaultTemplateResolver resolver = resolver("http://example.org/a/");

        assertEquals("http://example.org/a/io/bootique/mvc/resolver/tName.txt",
                resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
        assertEquals("http://example.org/a/io/bootique/mvc/resolver/tName.txt",
                resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
    }

    @Test
    public void testResourcePath_ClasspathBase()
            throws MalformedURLException
    {

        DefaultTemplateResolver resolver = resolver("classpath:");

        URL expected = baseClasspathUrl("io/bootique/mvc/resolver/tName.txt");
        assertEquals(expected, resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class));
        assertEquals(expected, resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class));
    }

    @Test
    public void testResourcePath_ClasspathBase_Slash()
            throws MalformedURLException
    {
        DefaultTemplateResolver resolver = resolver("classpath:/");
        assertEquals(baseClasspathUrl("io/bootique/mvc/resolver/tName.txt"),
                resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class));
    }

    private DefaultTemplateResolver resolver(String basePath) {
        return new DefaultTemplateResolver(new FolderResourceFactory(basePath), DEFAULT_CHARSET);
    }

    private URL baseClasspathUrl(String resourceRelativePath)
            throws MalformedURLException
    {
        return baseUrl("target/test-classes/", resourceRelativePath);
    }

    private URL baseUrl(String ... relativePaths)
            throws MalformedURLException
    {
        return Paths.get(System.getProperty("user.dir"), relativePaths).toUri().toURL();
    }
}
