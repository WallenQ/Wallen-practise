package com.wallen.test.spring.spring6.ioc.xml.di;

import lombok.Data;

/**
 * @author Wallen
 * @date 2024/10/18 11:38
 */
@Data
public class Book {
    private String name;
    private String author;
    private String otherParameter1;
    private String otherParameter2;
    private String otherParameter3;

    public Book() {
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Book(String name, String author, String otherParameter1, String otherParameter2, String otherParameter3) {
        this.name = name;
        this.author = author;
        this.otherParameter1 = otherParameter1;
        this.otherParameter2 = otherParameter2;
        this.otherParameter3 = otherParameter3;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", otherParameter1='" + otherParameter1 + '\'' +
                ", otherParameter2='" + otherParameter2 + '\'' +
                ", otherParameter3='" + otherParameter3 + '\'' +
                '}';
    }
}
