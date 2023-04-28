package com.codegym.demo.dto;

import com.codegym.demo.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ShoppingCartDto {
    private Integer id;

    private Product product;

    private String title;

    private Float price;

    private Integer quantity;
    private Boolean is_Deleted;

    public Boolean getIs_Deleted() {
        return is_Deleted;
    }

    public void setIs_Deleted(Boolean is_Deleted) {
        this.is_Deleted = is_Deleted;
    }

    public ShoppingCartDto() {
    }

    public ShoppingCartDto(Integer id, Product product,Integer quantity, String title, Float price) {
        this.id = id;
        this.product = product;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
