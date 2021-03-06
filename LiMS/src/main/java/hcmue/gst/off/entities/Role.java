package hcmue.gst.off.entities;

import com.fasterxml.jackson.annotation.*;
import hcmue.gst.off.extensions.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by WIN8.1 on 08/02/2017.
 */
@Entity
@Table(name = "role")
public class Role extends BaseEntity implements Serializable{
    private String name;

    private Set<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
