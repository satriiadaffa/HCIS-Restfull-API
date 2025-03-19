package site.satriiadaffa.hcis.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.satriiadaffa.hcis.app.model.EmployeeModel;
import site.satriiadaffa.hcis.app.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeModel> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public Optional<EmployeeModel> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public EmployeeModel addEmployee(EmployeeModel employeeModel) {
        return employeeRepository.save(employeeModel);
    }

//    public EmployeeModel updateEmployee(Long id, EmployeeModel employeeModel) {
//        return employeeRepository.findAll();
//    }

    public EmployeeModel updateEmployee(Long id, EmployeeModel newEmployeeData) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setNIK(newEmployeeData.getNIK());
                    existingEmployee.setName(newEmployeeData.getName());
                    existingEmployee.setEmail(newEmployeeData.getEmail());
                    existingEmployee.setPhone(newEmployeeData.getPhone());
                    existingEmployee.setGender(newEmployeeData.getGender());
                    existingEmployee.setAddress(newEmployeeData.getAddress());
                    return employeeRepository.save(existingEmployee);
                }).orElseThrow(() -> new RuntimeException("Employee dengan ID " + id + " tidak ditemukan"));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
