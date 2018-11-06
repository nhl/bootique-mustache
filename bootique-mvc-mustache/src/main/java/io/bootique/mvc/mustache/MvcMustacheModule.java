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

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import io.bootique.mvc.MvcModule;


import static io.bootique.mvc.mustache.MustacheTemplateRendererFactory.RENDERER_PREFIX;

public class MvcMustacheModule extends ConfigModule {

	@Override
	public void configure(Binder binder) {
		MvcModule.extend(binder).setRenderer(".mustache", MustacheTemplateRenderer.class);
	}

    /**
     * @since 1.0
     */
	@Singleton
	@Provides
	MustacheTemplateRenderer createTemplateRenderer(ConfigurationFactory configurationFactory) {
		return configurationFactory.config(MustacheTemplateRendererFactory.class, RENDERER_PREFIX).createRenderer();
	}
}
