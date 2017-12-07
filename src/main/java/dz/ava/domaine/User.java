package dz.ava.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

//@Data
//@AllArgsConstructor
public class User implements Serializable{

    private Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @Past
    private Date birthDate;

    public User() {
    }

    public User(Integer id, @Size(min = 2, message = "Name should have at least 2 characters") String name, @Past Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
