package com.nhl.bootique.mvc.resolver;

import com.nhl.bootique.resource.FolderResourceFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

public class DefaultTemplateResolverTest {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private DefaultTemplateResolver resolver(String basePath) {
        return new DefaultTemplateResolver(new FolderResourceFactory(basePath), DEFAULT_CHARSET);
    }

    private String baseCPAsFileUrl() {
        try {
            return new URL(baseFileUrl() + "target/test-classes/").toExternalForm();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Unexpected error", e);
        }
    }

    private String baseFileUrl() {
        try {
            return new URI(FolderResourceFactory.getUserDir()).toURL().toExternalForm() + "/";
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException("Unexpected error", e);
        }
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
    public void testResourcePath_FilePathBase() throws IOException {

        DefaultTemplateResolver resolver = resolver("/tmp");

        File canonical = new File("/tmp/com/nhl/bootique/mvc/resolver/tName.txt").getCanonicalFile();

        assertEquals(canonical.toURI().toURL(),
                resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class));
        assertEquals(canonical.toURI().toURL(),
                resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class));
    }

    @Test
    public void testResourcePath_FilePathBase_Slash() throws IOException {

        DefaultTemplateResolver resolver = resolver("/tmp/");

        File canonical = new File("/tmp/com/nhl/bootique/mvc/resolver/tName.txt").getCanonicalFile();

        assertEquals(canonical.toURI().toURL(),
                resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class));
        assertEquals(canonical.toURI().toURL(),
                resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class));
    }

    @Test
    public void testResourcePath_UrlBase() {

        DefaultTemplateResolver resolver = resolver("http://example.org/a");

        assertEquals("http://example.org/a/com/nhl/bootique/mvc/resolver/tName.txt",
                resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
        assertEquals("http://example.org/a/com/nhl/bootique/mvc/resolver/tName.txt",
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

    @Test
    public void testResourcePath_ClasspathBase_Slash() {
        DefaultTemplateResolver resolver = resolver("classpath:/");
        assertEquals(baseCPAsFileUrl() + "com/nhl/bootique/mvc/resolver/tName.txt",
                resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
    }

}
