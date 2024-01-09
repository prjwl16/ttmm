package ttmm.database.models;

import io.ebean.annotation.Index;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseModel {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    @Index(unique = true)
    private String email;
    private String avatar;
    @Column(name = "google_auth_id")
    private String googleAuthId;

    //Many to Many
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_group",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    private List<Group> groups;

}
