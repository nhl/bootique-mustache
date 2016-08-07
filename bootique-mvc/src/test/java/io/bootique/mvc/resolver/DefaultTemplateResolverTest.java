package io.bootique.mvc.resolver;

import io.bootique.resource.FolderResourceFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

public class DefaultTemplateResolverTest {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private DefaultTemplateResolver resolver(String basePath) {
        return new DefaultTemplateResolver(new FolderResourceFactory(basePath), DEFAULT_CHARSET);
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

        assertEquals(baseFileUrl() + "io/bootique/mvc/resolver/tName.txt",
                resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
        assertEquals(baseFileUrl() + "io/bootique/mvc/resolver/tName.txt",
                resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
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
    public void testResourcePath_ClasspathBase() {

        DefaultTemplateResolver resolver = resolver("classpath:");

        assertEquals(baseCPAsFileUrl() + "io/bootique/mvc/resolver/tName.txt",
                resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
        assertEquals(baseCPAsFileUrl() + "io/bootique/mvc/resolver/tName.txt",
                resolver.resourceUrl("/tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
    }

    @Test
    public void testResourcePath_ClasspathBase_Slash() {
        DefaultTemplateResolver resolver = resolver("classpath:/");
        assertEquals(baseCPAsFileUrl() + "io/bootique/mvc/resolver/tName.txt",
                resolver.resourceUrl("tName.txt", DefaultTemplateResolverTest.class).toExternalForm());
    }

}
