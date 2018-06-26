package com.ming.entity;

import java.io.Serializable;

public class DempEntity implements Serializable {

    private static final long serialVersionUID = -5286067973983139766L;
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}