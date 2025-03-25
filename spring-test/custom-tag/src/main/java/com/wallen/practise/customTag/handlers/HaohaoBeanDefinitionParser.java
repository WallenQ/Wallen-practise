package com.wallen.practise.customTag.handlers;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author Wallen
 * @date 2025/3/25 17:10
 */
public class HaohaoBeanDefinitionParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        //注入一个BeanPostProcessor
        BeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClassName("com.wallen.practise.customTag.processor.HaohaoBeanPostProcessor");
        parserContext.getRegistry().registerBeanDefinition("haohaoBeanPostProcessor",beanDefinition);
        return beanDefinition;
    }
}
