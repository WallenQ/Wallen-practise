package com.wallen.bean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Wallen
 * @date 2023/9/5 19:00
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        boolean flag = importingClassMetadata.hasAnnotation("org.springframework.context.annotation.Import");
        if (flag) {
            return new String[]{"com.wallen.bean.Dog"};
        }
        return new String[]{"com.wallen.bean.Cat"};
    }
}
