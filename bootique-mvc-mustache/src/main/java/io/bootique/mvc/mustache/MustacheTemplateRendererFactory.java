package io.bootique.mvc.mustache;

import io.bootique.annotation.BQConfig;
import io.bootique.annotation.BQConfigProperty;

import java.util.concurrent.Executors;

@BQConfig
public class MustacheTemplateRendererFactory {
    public static final String RENDERER_PREFIX = "mustacheRenderer";

    private int poolSize;

    public int getPoolSize() {
        return poolSize;
    }

    @BQConfigProperty
    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public MustacheTemplateRenderer createRenderer() {
        return  poolSize == 0 ? new MustacheTemplateRenderer()
                : new MustacheTemplateRenderer(Executors.newFixedThreadPool(poolSize));
    }
}
