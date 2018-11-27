package com.wing.pi.tools.util;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("asas");
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;
    private Long age;

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
