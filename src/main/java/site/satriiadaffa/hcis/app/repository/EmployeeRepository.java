package site.satriiadaffa.hcis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.satriiadaffa.hcis.app.model.EmployeeModel;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel,Long> {
}
