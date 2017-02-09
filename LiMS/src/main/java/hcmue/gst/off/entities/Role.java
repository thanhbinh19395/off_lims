package hcmue.gst.off.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by WIN8.1 on 08/02/2017.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable{
    private long id;
    private String name;
    private Set<User> users;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
