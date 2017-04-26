package io.bootique.mvc.freemarker;

import io.bootique.jersey.JerseyModule;
import io.bootique.jetty.test.junit.JettyTestFactory;
import io.bootique.mvc.freemarker.views.hierarchy.PageView;
import io.bootique.mvc.freemarker.views.HelloWorldView;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.Collections;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static org.junit.Assert.assertEquals;

public class MvcFreemarkerModuleIT {

	@ClassRule
	public static JettyTestFactory TEST_SERVER = new JettyTestFactory();

	@BeforeClass
	public static void beforeClass() {
		TEST_SERVER.app().args("--config=classpath:MvcFreemarkerModuleIT.yml").autoLoadModules()
				  .module(binder -> JerseyModule.contributeResources(binder).addBinding().to(Api.class)).start();
	}

	@Test
	public void shouldRenderPageThatUsesHierarchicalLayoutWithAllSectionsPopulated() {
		WebTarget base = ClientBuilder.newClient().target("http://localhost:8080");
		Response r1 = base.path("/hierarchy-all").request().get();
		assertEquals(Status.OK.getStatusCode(), r1.getStatus());
		assertEquals("This is custom header.This is custom content.This is custom footer.", r1.readEntity(String.class));
	}

	@Test
	public void shouldRenderPageThatUsesHierarchicalLayoutWithJustSomeSectionsPopulated() {
		WebTarget base = ClientBuilder.newClient().target("http://localhost:8080");
		Response r1 = base.path("/hierarchy-some").request().get();
		assertEquals(Status.OK.getStatusCode(), r1.getStatus());
		assertEquals("Default header.This is custom content.", r1.readEntity(String.class));
	}

	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public static class Api {

		@GET
		@Path("/hello")
		public HelloWorldView getV1() {
			return new HelloWorldView("hello-world.ftl", "John", "Doe");
		}

		@GET
		@Path("/hierarchy-all")
		public PageView getAllMacrosPage() {
			return new PageView("all-macros.ftl", Collections.emptyMap());
		}

		@GET
		@Path("/hierarchy-some")
		public PageView getOneMacroPage() {
			return new PageView("one-macro.ftl", Collections.emptyMap());
		}

	}
}
