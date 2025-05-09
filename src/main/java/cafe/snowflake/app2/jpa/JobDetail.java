package cafe.snowflake.app2.jpa;

import cafe.snowflake.app2.model.AppDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDetail extends JpaRepository<AppDetail, Integer> {

}
