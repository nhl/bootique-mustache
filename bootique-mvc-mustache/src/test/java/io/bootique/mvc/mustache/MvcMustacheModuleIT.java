/*
 * Licensed to ObjectStyle LLC under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ObjectStyle LLC licenses
 * this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.bootique.mvc.mustache;

import io.bootique.jersey.JerseyModule;
import io.bootique.mvc.mustache.view.ConcreteView;
import io.bootique.test.junit.BQTestFactory;
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

import static org.junit.Assert.assertEquals;

public class MvcMustacheModuleIT {

    @ClassRule
    public static BQTestFactory TEST_SERVER = new BQTestFactory();

    @BeforeClass
    public static void beforeClass() {
        TEST_SERVER.app()
                .args("--config=classpath:MvcMustacheModuleIT.yml", "-s")
                .autoLoadModules()
                .module(binder -> JerseyModule.extend(binder).addResource(Api.class))
                .run();
    }

    @Test
    public void testV1() {
        WebTarget base = ClientBuilder.newClient().target("http://localhost:8080");
        Response r1 = base.path("/v1").request().get();
        assertEquals(Status.OK.getStatusCode(), r1.getStatus());
        assertEquals("\nv1_string_p1_number_564", r1.readEntity(String.class));
    }

    @Test
    public void testV2() {
        WebTarget base = ClientBuilder.newClient().target("http://localhost:8080");
        Response r1 = base.path("/v2").request().get();
        assertEquals(Status.OK.getStatusCode(), r1.getStatus());
        assertEquals("\nv2_string_p2_number_5649", r1.readEntity(String.class));
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

        public void setProp1(String prop1) {
            this.prop1 = prop1;
        }

        public int getProp2() {
            return prop2;
        }

        public void setProp2(int prop2) {
            this.prop2 = prop2;
        }
    }
}
