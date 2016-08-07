package io.bootique.mvc.mustache;

import io.bootique.test.junit.BQModuleProviderChecker;
import org.junit.Test;

public class MvcMustacheModuleProviderTest {

	@Test
	public void testPresentInJar() {
		BQModuleProviderChecker.testPresentInJar(MvcMustacheModuleProvider.class);
	}
}
