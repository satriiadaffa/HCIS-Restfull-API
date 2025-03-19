package site.satriiadaffa.hcis.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.satriiadaffa.hcis.app.model.management.DepartmentModel;
import site.satriiadaffa.hcis.app.model.management.PositionModel;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employees")
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "NIK Tidak Boleh Kosong")
    @Pattern(regexp = "\\d{16,16}", message = "NIK harus 16 digit angka")
    private String NIK;

    @NotBlank(message = "Nama Tidak Boleh Kosong")
    @Size(min = 2, max = 100, message = "Nama harus 2-100 karakter")
    private String name;

    @NotBlank(message = "Email Tidak Boleh Kosong")
    @Email(message = "Email tidak valid")
    private String email;

    @NotBlank(message = "Nomor Telefon Tidak Boleh Kosong")
    @Pattern(regexp = "\\d{10,15}", message = "Nomor telepon harus 10-15 digit angka")
    private String phone;

    // Gender sebagai boolean (false = Pria, true = Wanita)
    @Pattern(regexp = "Male|Female", message = "Jenis Kelamin harus 'Male' atau 'Female'")
    private String gender;

    @NotBlank(message = "Alamat Tidak Boleh Kosong")
    @Size(min = 10, max = 255, message = "Alamat harus 10-255 karakter")
    private String address;


    @ManyToOne
    @JoinColumn(name = "current_position_id")
    private PositionModel currentPosition;

    @ManyToOne
    @JoinColumn(name = "current_department_id")
    private DepartmentModel currentDepartment;
}
