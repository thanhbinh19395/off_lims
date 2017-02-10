package com.gst.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Entity
@Table(name = "role")
public class Role extends Base {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
