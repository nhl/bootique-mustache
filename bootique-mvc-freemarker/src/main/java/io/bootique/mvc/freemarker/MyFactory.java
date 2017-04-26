package io.bootique.mvc.freemarker;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.bootique.annotation.BQConfig;
import io.bootique.annotation.BQConfigProperty;
import io.bootique.resource.FolderResourceFactory;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Lukasz Bachman
 */
@BQConfig("Configures MVC template resolver.")
public class MyFactory {

    private FolderResourceFactory templateBase;
    private String hierarchyTemplatePrefix;

    public MyFactory() {
        this.templateBase = new FolderResourceFactory("");
    }

    @BQConfigProperty("TODO")
    public void setTemplateBase(FolderResourceFactory templateBase) {
        this.templateBase = Objects.requireNonNull(templateBase);
    }

    @BQConfigProperty("TODO")
    public void setHierarchyTemplatePrefix(String hierarchyTemplatePrefix) {
        this.hierarchyTemplatePrefix = hierarchyTemplatePrefix;
    }



}
