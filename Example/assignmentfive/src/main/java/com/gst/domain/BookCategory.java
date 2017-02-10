package com.gst.domain;

import com.gst.controller.BaseController;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Entity
@Table(name = "bookcategory")
public class BookCategory extends Base implements Serializable {
    private String category_name;


    public BookCategory() {
    }

    public BookCategory(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }


}
