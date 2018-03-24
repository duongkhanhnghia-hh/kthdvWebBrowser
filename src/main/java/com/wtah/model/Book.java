package com.haku.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String id;
    private String code;
    private String name;
    private Integer quantityInStock;
    private String author;

    public Book() {
    }

    public Book(String id, String code, String name, Integer quantityInStock, String author) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantityInStock = quantityInStock;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
