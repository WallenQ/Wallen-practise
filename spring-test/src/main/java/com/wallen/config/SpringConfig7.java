package com.wallen.config;

import com.wallen.bean.MyImportBeanDefinitionRegistrar;
import com.wallen.bean.MyImportSelector;
import org.springframework.context.annotation.Import;

/**
 * @author Wallen
 * @date 2023/9/5 18:53
 */
@Import(MyImportBeanDefinitionRegistrar.class)
public class SpringConfig7 {

}
