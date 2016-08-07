package io.bootique.mvc;

import io.bootique.test.junit.BQModuleProviderChecker;
import org.junit.Test;

public class MvcModuleProviderTest {

	@Test
	public void testPresentInJar() {
		BQModuleProviderChecker.testPresentInJar(MvcModuleProvider.class);
	}
}
