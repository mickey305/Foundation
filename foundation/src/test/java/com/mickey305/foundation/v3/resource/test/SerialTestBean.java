package com.mickey305.foundation.v3.resource.test;

import java.io.Serializable;

public class SerialTestBean implements Serializable {
    private static final long serialVersionUID = 3692887677365620098L;
    private String name;
    private long id;

    public SerialTestBean(long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
