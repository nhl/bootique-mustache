package com.nhl.bootique.mvc.mustache;

import static org.junit.Assert.assertEquals;

import java.util.function.Consumer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com.nhl.bootique.Bootique;
import com.nhl.bootique.jersey.JerseyModule;
import com.nhl.bootique.jetty.test.junit.JettyTestFactory;
import com.nhl.bootique.mvc.mustache.view.ConcreteView;

public class MvcMustacheModuleIT {

	@ClassRule
	public static JettyTestFactory TEST_SERVER = new JettyTestFactory();

	@BeforeClass
	public static void beforeClass() {

		JerseyModule jersey = JerseyModule.builder().resource(Api.class).build();

		Consumer<Bootique> configurator = bq -> bq.autoLoadModules().module(jersey);
		TEST_SERVER.newRuntime().configurator(configurator).startServer();
	}

	@Test
	public void testV1() {
		WebTarget base = ClientBuilder.newClient().target("http://localhost:8080");
		Response r1 = base.path("/v1").request().get();
		assertEquals(Status.OK.getStatusCode(), r1.getStatus());
		assertEquals("v1_string_p1_number_564", r1.readEntity(String.class));
	}

	@Test
	public void testV2() {
		WebTarget base = ClientBuilder.newClient().target("http://localhost:8080");
		Response r1 = base.path("/v2").request().get();
		assertEquals(Status.OK.getStatusCode(), r1.getStatus());
		assertEquals("v2_string_p2_number_5649", r1.readEntity(String.class));
	}

	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public static class Api {

		@GET
		@Path("/v1")
		public ConcreteView getV1() {
			Model m = new Model();
			m.setProp1("p1");
			m.setProp2(564);
			return new ConcreteView("MvcMustacheModuleIT_v1.mustache", m);
		}

		@GET
		@Path("/v2")
		public ConcreteView getV2() {
			Model m = new Model();
			m.setProp1("p2");
			m.setProp2(5649);
			return new ConcreteView("MvcMustacheModuleIT_v2.mustache", m);
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
