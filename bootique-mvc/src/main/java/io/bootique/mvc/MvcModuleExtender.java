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

package io.bootique.mvc;

import com.google.inject.Binder;
import com.google.inject.Singleton;
import com.google.inject.multibindings.MapBinder;
import io.bootique.mvc.renderer.TemplateRenderer;

/**
 * @since 0.6
 */
public class MvcModuleExtender {

    private Binder binder;
    private MapBinder<String, TemplateRenderer> templateRenderers;

    MvcModuleExtender(Binder binder) {
        this.binder = binder;
    }

    MvcModuleExtender initAllExtensions() {
        contributeRenderers();
        return this;
    }

    public MvcModuleExtender setRenderer(String handledExtension, TemplateRenderer renderer) {
        contributeRenderers().addBinding(handledExtension).toInstance(renderer);
        return this;
    }

    public MvcModuleExtender setRenderer(String handledExtension, Class<? extends TemplateRenderer> rendererType) {
        // TODO: what does singleton scope means when adding to collection?
        contributeRenderers().addBinding(handledExtension).to(rendererType).in(Singleton.class);
        return this;
    }

    protected MapBinder<String, TemplateRenderer> contributeRenderers() {
        if (templateRenderers == null) {
            templateRenderers = MapBinder.newMapBinder(binder, String.class, TemplateRenderer.class);
        }
        return templateRenderers;
    }

}
