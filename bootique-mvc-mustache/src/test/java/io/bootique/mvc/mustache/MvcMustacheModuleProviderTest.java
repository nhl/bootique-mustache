package io.bootique.mvc.mustache;

import io.bootique.BQRuntime;
import io.bootique.jersey.JerseyModule;
import io.bootique.mvc.MvcModule;
import io.bootique.test.junit.BQModuleProviderChecker;
import io.bootique.test.junit.BQTestFactory;
import org.junit.Rule;
import org.junit.Test;

import static com.google.common.collect.ImmutableList.of;

public class MvcMustacheModuleProviderTest {

    @Rule
    public BQTestFactory testFactory = new BQTestFactory();

    @Test
    public void testPresentInJar() {
        BQModuleProviderChecker.testPresentInJar(MvcMustacheModuleProvider.class);
    }

    @Test
    public void testModuleDeclaresDependencies() {
        final BQRuntime bqRuntime = testFactory.app().module(new MvcMustacheModuleProvider()).createRuntime();
        BQModuleProviderChecker.testModulesLoaded(bqRuntime, of(
                JerseyModule.class,
                MvcModule.class,
                MvcMustacheModule.class
        ));
    }
}
