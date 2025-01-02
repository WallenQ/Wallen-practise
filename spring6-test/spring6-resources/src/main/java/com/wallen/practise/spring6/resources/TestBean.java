package com.wallen.practise.spring6.resources;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

/**
 * @author Wallen
 * @date 2025/1/2 10:13
 */
public class TestBean implements ResourceLoaderAware {
    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
