package ttmm.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "users")
public class User extends BaseModel {
    private String first_name;
    private String last_name;
    private String email;
    private String avatar;
    private String google_auth_id;
}
