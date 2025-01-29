package com.wallen.bean;

import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author wallen
 * @date 2023/8/19 15:42
 */
@Data
@Service("Jerry")
public class Mouse {
    private String name;
    private int age;
}
