package ttmm.database.models;

import io.ebean.annotation.NotNull;
import io.ebean.annotation.SoftDelete;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.sql.Timestamp;

@Data
@MappedSuperclass
public class BaseModel {

    @Id
    @NotNull
    @Column(length = 40)
    private Long id;

    @WhenCreated
    @Column(name = "created_at")
    private Timestamp createdAt;

    @WhenModified
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @SoftDelete
    private boolean deleted;


}
