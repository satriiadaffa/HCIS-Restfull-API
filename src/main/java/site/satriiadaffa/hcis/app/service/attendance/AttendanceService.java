package site.satriiadaffa.hcis.app.service.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.satriiadaffa.hcis.app.dto.attendance.AttendanceDTO;
import site.satriiadaffa.hcis.app.model.attendance.AttendanceModel;
import site.satriiadaffa.hcis.app.model.attendance.AttendanceSummaryModel;
import site.satriiadaffa.hcis.app.repository.attendance.AttendanceRepository;
import site.satriiadaffa.hcis.app.repository.attendance.AttendanceSummaryRepository;

import java.time.*;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private AttendanceSummaryRepository summaryRepository;

    public AttendanceModel clockIn(AttendanceDTO request) {
        Long employeeId = request.getEmployeeId();
        LocalDate today = LocalDate.now();

        // Periksa apakah sudah Clock-In hari ini
        if (attendanceRepository.findByEmployeeIdAndDate(employeeId, today).isPresent()) {
            throw new IllegalStateException("Already clocked in today.");
        }

        // Buat objek AttendanceModel dengan date
        AttendanceModel attendance = new AttendanceModel();
        attendance.setEmployeeId(employeeId);
        attendance.setDate(today); // ✅ Pastikan nilai date diisi
        attendance.setClockInTime(LocalDateTime.now());
        attendance.setLocation(request.getLocation());
        attendance.setStatus("Present");

        // Simpan ke database
        return attendanceRepository.save(attendance);
    }


    public AttendanceModel clockOut(Long employeeId) {
        AttendanceModel attendance = attendanceRepository.findLatestByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("No clock-in record found"));

        if (attendance.getClockOutTime() != null) {
            throw new IllegalStateException("Already clocked out");
        }

        attendance.setClockOutTime(LocalDateTime.now());
        attendance.setWorkHours(Duration.between(attendance.getClockInTime(), attendance.getClockOutTime()).toHours());
        attendanceRepository.save(attendance);

        updateAttendanceSummary(employeeId);

        return attendance;
    }

    private void updateAttendanceSummary(Long employeeId) {
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();

        int totalPresent = attendanceRepository.countDays(employeeId, month, year, "Present");
        int totalAbsent = attendanceRepository.countDays(employeeId, month, year, "Absent");
        double totalWorkHours = attendanceRepository.sumWorkHours(employeeId, month, year);

        AttendanceSummaryModel summary = summaryRepository.findByEmployeeAndMonthYear(employeeId, month, year)
                .orElse(new AttendanceSummaryModel());

        summary.setEmployeeId(employeeId);
        summary.setMonth(month);
        summary.setYear(year);
        summary.setTotalPresent(totalPresent);
        summary.setTotalAbsent(totalAbsent);
        summary.setTotalWorkHours(totalWorkHours);

        summaryRepository.save(summary);
    }

    public Optional<AttendanceSummaryModel> getAttendanceSummary(Long employeeId, int month, int year) {
        return summaryRepository.findByEmployeeAndMonthYear(employeeId, month, year);
    }
}
