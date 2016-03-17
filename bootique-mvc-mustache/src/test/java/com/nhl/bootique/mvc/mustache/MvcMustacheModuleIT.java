package com.nhl.bootique.mvc.mustache;

import static org.junit.Assert.assertEquals;

import java.util.function.Consumer;
import java.util.function.Function;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.server.mvc.Template;
import org.glassfish.jersey.server.mvc.Viewable;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com.nhl.bootique.BQRuntime;
import com.nhl.bootique.Bootique;
import com.nhl.bootique.jersey.JerseyModule;
import com.nhl.bootique.test.junit.BQDaemonTestFactory;

public class MvcMustacheModuleIT {

	@ClassRule
	public static BQDaemonTestFactory testFactory = new BQDaemonTestFactory();

	@BeforeClass
	public static void beforeClass() {

		JerseyModule jersey = JerseyModule.builder().resource(Api.class).build();

		Consumer<Bootique> configurator = bq -> bq.autoLoadModules().module(jersey);
		Function<BQRuntime, Boolean> startupCheck = r -> r.getInstance(Server.class).isStarted();
		testFactory.newRuntime().configurator(configurator).startupCheck(startupCheck).start("--server");
	}

	@Test
	public void testViewable() {
		WebTarget base = ClientBuilder.newClient().target("http://localhost:8080");
		Response r1 = base.path("/viewable").request().get();
		assertEquals(Status.OK.getStatusCode(), r1.getStatus());
		assertEquals("viewable_string_p1_number_564", r1.readEntity(String.class));
	}

	@Test
	public void testTemplate() {
		WebTarget base = ClientBuilder.newClient().target("http://localhost:8080");
		Response r1 = base.path("/template").request().get();
		assertEquals(Status.OK.getStatusCode(), r1.getStatus());
		assertEquals("template_string_p1v_number_5649", r1.readEntity(String.class));
	}
	
	@Test
	public void testTemplateNoExt() {
		WebTarget base = ClientBuilder.newClient().target("http://localhost:8080");
		Response r1 = base.path("/template_no_ext").request().get();
		assertEquals(Status.OK.getStatusCode(), r1.getStatus());
		assertEquals("template_string_p1vv_number_56490", r1.readEntity(String.class));
	}

	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public static class Api {

		@GET
		@Path("/template")
		@Template(name = "MvcMustacheModuleIT_template.mustache")
		public Model getTemplate() {
			Model m = new Model();
			m.setProp1("p1v");
			m.setProp2(5649);
			return m;
		}
		
		@GET
		@Path("/template_no_ext")
		@Template(name = "MvcMustacheModuleIT_template")
		public Model getTemplateNoExt() {
			Model m = new Model();
			m.setProp1("p1vv");
			m.setProp2(56490);
			return m;
		}

		@GET
		@Path("/viewable")
		public Viewable getViewable() {
			Model m = new Model();
			m.setProp1("p1");
			m.setProp2(564);
			return new Viewable("MvcMustacheModuleIT_viewable.mustache", m);
		}
	}

	public static class Model {
		private String prop1;
		private int prop2;

		public String getProp1() {
			return prop1;
		}

		public int getProp2() {
			return prop2;
		}

		public void setProp1(String prop1) {
			this.prop1 = prop1;
		}

		public void setProp2(int prop2) {
			this.prop2 = prop2;
		}
	}
}
