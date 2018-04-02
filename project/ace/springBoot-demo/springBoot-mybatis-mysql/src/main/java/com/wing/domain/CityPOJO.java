package com.wing.ace.jzvz.springboot.domain;/**
 * Created by Administrator on 2017/4/6.
 */

/**
 * description city
 * author stephen.cui
 * date 2017/4/6 21:43
 **/
public class CityPOJO {
    private Long id;

    private String name;

    private String state;

    private String country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
