package ttmm.database.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "group")
public class Group extends BaseModel {
    private String name;
    private String description;
    private String avatar;

    @ManyToMany(mappedBy = "groups")
    private List<User> users;
}
