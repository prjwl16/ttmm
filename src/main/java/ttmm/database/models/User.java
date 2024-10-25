package ttmm.database.models;

import io.ebean.annotation.DbDefault;
import io.ebean.annotation.Index;
import io.ebean.annotation.JsonIgnore;
import io.ebean.annotation.NotNull;
import lombok.*;
import ttmm.database.enums.Role;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseModel {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Index(unique = true)
    @NotNull
    private String email;

    private String avatar;

    @Column(name = "google_auth_id")
    private String googleAuthId;

    @Enumerated(EnumType.STRING)
    @DbDefault("USER")
    @NotNull
    private Role role=Role.USER;

//    @OneToMany(mappedBy = "creator")
//    @JsonIgnore
//    private List<UserTransaction> userTransactions;
//
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<Category> categories;
//
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<UserGroup> userGroups;


    public User(String email, String firstName, String lastName, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

}
