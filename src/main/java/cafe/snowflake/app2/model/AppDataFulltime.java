package cafe.snowflake.app2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class AppDataFulltime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String company;
    private String jobUrl;
    private String details;
    private String website;
    private int applied;
    private Date created;
    private Date updated;
    private String notes;

    @Transient
    List<AppDetailFulltime> tagList;

}
