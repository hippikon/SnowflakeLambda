package cafe.snowflake.app2.jpa;

import cafe.snowflake.app2.model.AppDetailFulltime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FulltimeJobDetail extends JpaRepository<AppDetailFulltime, Integer> {

}
