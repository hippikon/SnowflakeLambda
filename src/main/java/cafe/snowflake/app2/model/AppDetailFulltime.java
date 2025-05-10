package cafe.snowflake.app2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class AppDetailFulltime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int jobId;
    private String tagName;
    private String tagValue;
    private Date created;
    private Date updated;

    // Constructors, getters, and setters
}
