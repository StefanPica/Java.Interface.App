package com.example.javainterf.Domain;

import java.io.Serializable;

public interface IEntity extends Serializable {

    static final long serialVersionUID = 1L;

    void setId(Integer id);

    Integer getId();
}
