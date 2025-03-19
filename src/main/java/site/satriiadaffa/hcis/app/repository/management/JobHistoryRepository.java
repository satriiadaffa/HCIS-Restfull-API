package site.satriiadaffa.hcis.app.repository.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.satriiadaffa.hcis.app.model.management.JobHistoryModel;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistoryModel,Long> {
}
