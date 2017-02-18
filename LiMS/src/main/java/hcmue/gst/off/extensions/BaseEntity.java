package hcmue.gst.off.extensions;

import com.fasterxml.jackson.annotation.*;
import hcmue.gst.off.entities.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by WIN8.1 on 08/02/2017.
 */
@MappedSuperclass
public class BaseEntity {
    private Long id;
    private User created_by;
    private Date created_date;
    private User update_by;
    private Date update_date;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "created_by")
    @JsonIgnore
    public User getCreated_by() {
        return created_by;
    }

    public void setCreated_by(User created_by) {
        this.created_by = created_by;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    @ManyToOne
    @JoinColumn(name = "updated_by")
    @JsonIgnore
    public User getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(User update_by) {
        this.update_by = update_by;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
}
