package com.wallen.practise.spring6.resources;

import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Wallen
 * @date 2024/12/31 18:03
 */
public class FileSystemResourceDemo {
    public static void main(String[] args) {
        loadAndReadUrlResource("/Users/wallen/GitHub/Wallen-practise/spring6-test/spring6-resources/src/main/resources/test.txt");
    }

    public static void loadAndReadUrlResource(String path) {
        FileSystemResource resource = new FileSystemResource(path);
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());
        InputStream inputStream;
        try {
            inputStream = resource.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = new byte[1024];
        while (true) {
            try {
                if (inputStream.read(bytes) == -1) {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(new String(bytes));
        }
    }
}
