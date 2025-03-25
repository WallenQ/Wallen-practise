package com.wallen.practise.customTag.handlers;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author Wallen
 * @date 2025/3/25 17:07
 */
public class HaohaoNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        this.registerBeanDefinitionParser("annotation-driven", new HaohaoBeanDefinitionParser());
    }
}
