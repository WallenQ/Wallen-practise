package com.wallen.practise.spring6.resources;

import org.springframework.core.io.UrlResource;

import java.io.IOException;

/**
 * @author Wallen
 * @date 2024/12/31 17:46
 */
public class UrlResourceLoaderDemo {
    public static void main(String[] args) {
        //访问网络资源
        loadAndReadUrlResource("http://www.baidu.com");
        System.out.println("------------------------------------");
        //访问文件系统资源
        loadAndReadUrlResource("file:/Users/wallen/GitHub/Wallen-practise/spring6-test/spring6-resources/src/main/resources/test.txt");
    }

    public static void loadAndReadUrlResource(String path) {
        try {
            //创建一个 Resource 对象
            UrlResource urlResource = new UrlResource(path);
            System.out.println(urlResource.getFilename());
            System.out.println(urlResource.getURL());
            System.out.println(urlResource.getDescription());
            System.out.println(urlResource.getInputStream().read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
