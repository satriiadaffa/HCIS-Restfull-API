package site.satriiadaffa.hcis.app.repository.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.satriiadaffa.hcis.app.model.management.DepartmentModel;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentModel,Long> {
}
