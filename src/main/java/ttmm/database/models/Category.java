package ttmm.database.models;

import io.ebean.annotation.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseModel {
    @NotNull
    private String name;
    private String description;
    private String avatar;

    //Relationships

    @ManyToOne
    @Column(name = "user_id")
    private User user;
}
