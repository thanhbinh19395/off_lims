package hcmue.gst.off.entities;

/**
 * Created by Thanh Binh on 2/12/2017.
 */

import hcmue.gst.off.extensions.EntityBase;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
@Entity
public class Country extends EntityBase {
    private static final long serialVersionUID = 1L;

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


    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }
}