package com.nhl.bootique.mvc;

import org.junit.Test;

import com.nhl.bootique.test.junit.BQModuleProviderChecker;

public class MvcModuleProviderTest {

	@Test
	public void testPresentInJar() {
		BQModuleProviderChecker.testPresentInJar(MvcModuleProvider.class);
	}
}
