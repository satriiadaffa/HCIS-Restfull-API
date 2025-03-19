package site.satriiadaffa.hcis.app.service.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.satriiadaffa.hcis.app.model.management.DepartmentModel;
import site.satriiadaffa.hcis.app.repository.management.DepartmentRepository;

import java.util.*;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentModel> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<DepartmentModel> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public DepartmentModel createDepartment(DepartmentModel department) {
        return departmentRepository.save(department);
    }

    public DepartmentModel updateDepartment(Long id, DepartmentModel departmentDetails) {
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setDepartmentName(departmentDetails.getDepartmentName());
                    return departmentRepository.save(department);
                })
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
