package cafe.snowflake.app2.jpa;

import cafe.snowflake.app2.model.AppDataFulltime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FulltimeJobApplicationDetail extends JpaRepository<AppDataFulltime, Integer> {
    List<AppDataFulltime> findByName(String name);
    List<AppDataFulltime> findByJobUrl(String jobUrl);

}