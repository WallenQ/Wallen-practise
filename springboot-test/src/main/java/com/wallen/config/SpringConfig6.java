package com.wallen.config;

import com.wallen.bean.MyImportSelector;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Wallen
 * @date 2023/9/5 18:53
 */
@Import(MyImportSelector.class)
public class SpringConfig6 {

}
