package com.wallen.bean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Wallen
 * @date 2023/9/5 19:00
 */
public class MyImportSelector9 implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        try {
            Class.forName("com.wallen.bean.Mouse");
            return new String[]{"com.wallen.bean.Cat"};
        } catch (ClassNotFoundException e) {
            return new String[0];
        }
    }
}
