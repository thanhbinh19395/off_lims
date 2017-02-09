package com.gst.domain;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by Thanh Binh on 2/8/2017.
 */
@Entity
public class Country {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @NotEmpty
    @Column(name = "countryname", nullable = false)
    private String countryname;

    public Country() {
        super();
    }

    public Country(String countryname) {
        super();
        this.countryname = countryname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }
}
