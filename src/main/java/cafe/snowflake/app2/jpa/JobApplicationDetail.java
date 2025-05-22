package cafe.snowflake.app2.jpa;

import cafe.snowflake.app2.model.AppData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationDetail extends JpaRepository<AppData, Integer> {
    List<AppData> findByName(String name);

}