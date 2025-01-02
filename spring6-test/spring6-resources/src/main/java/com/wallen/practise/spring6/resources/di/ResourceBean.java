package com.wallen.practise.spring6.resources.di;

import lombok.Data;
import org.springframework.core.io.Resource;

/**
 * @author Wallen
 * @date 2025/1/2 10:26
 */
@Data
public class ResourceBean {
    private Resource resource;

    public void parse() {
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());
    }
}
