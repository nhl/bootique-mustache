package com.nhl.bootique.mvc.mustache;

import org.junit.Test;

import com.nhl.bootique.mvc.mustache.MvcMustacheModuleProvider;
import com.nhl.bootique.test.junit.BQModuleProviderChecker;

public class MvcMustacheModuleProviderTest {

	@Test
	public void testPresentInJar() {
		BQModuleProviderChecker.testPresentInJar(MvcMustacheModuleProvider.class);
	}
}
