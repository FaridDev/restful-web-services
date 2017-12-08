package dz.ava.domaine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "All details about the user")
@Data
@AllArgsConstructor
public class User implements Serializable{

    private Integer id;

    @ApiModelProperty("Name should have at least 2 characters")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @ApiModelProperty(notes = "Birth date should be in the past")
    @Past
    private Date birthDate;
}
