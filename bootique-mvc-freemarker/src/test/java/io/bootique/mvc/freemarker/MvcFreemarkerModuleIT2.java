package io.bootique.mvc.freemarker;

import io.bootique.jersey.JerseyModule;
import io.bootique.jetty.test.junit.JettyTestFactory;
import io.bootique.mvc.freemarker.views.HelloWorldView;
import io.bootique.mvc.freemarker.views.hierarchy.PageView;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * @author Lukasz Bachman
 */
public class MvcFreemarkerModuleIT2 {

    @ClassRule
    public static JettyTestFactory TEST_SERVER = new JettyTestFactory();

    @BeforeClass
    public static void beforeClass() {
        TEST_SERVER.app().args("--config=classpath:MvcFreemarkerModuleIT2.yml").autoLoadModules()
                .module(binder -> JerseyModule.contributeResources(binder).addBinding().to(Api.class)).start();
    }

    @Test
    public void shouldRenderSimpleHelloMessage() {
        WebTarget base = ClientBuilder.newClient().target("http://localhost:8080");
        Response r1 = base.path("/hello").request().get();
        assertEquals(Status.OK.getStatusCode(), r1.getStatus());
        assertEquals("Hello John Doe!", r1.readEntity(String.class));
    }

    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public static class Api {

        @GET
        @Path("/hello")
        public HelloWorldView getV1() {
            return new HelloWorldView("hello-world.ftl", "John", "Doe");
        }

    }
}
