package site.satriiadaffa.hcis.app.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.satriiadaffa.hcis.app.model.management.DepartmentModel;
import site.satriiadaffa.hcis.app.service.management.DepartmentService;

import java.util.*;

@RestController
@RequestMapping("/api/departments")
public class DepartementController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<DepartmentModel> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Optional<DepartmentModel> getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public DepartmentModel createDepartment(@RequestBody DepartmentModel department) {
        return departmentService.createDepartment(department);
    }

    @PutMapping("/{id}")
    public DepartmentModel updateDepartment(@PathVariable Long id, @RequestBody DepartmentModel department) {
        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}



