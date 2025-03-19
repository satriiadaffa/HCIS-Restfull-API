package site.satriiadaffa.hcis.app.controller.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.satriiadaffa.hcis.app.dto.attendance.AttendanceDTO;
import site.satriiadaffa.hcis.app.model.attendance.AttendanceModel;
import site.satriiadaffa.hcis.app.model.attendance.AttendanceSummaryModel;
import site.satriiadaffa.hcis.app.service.attendance.AttendanceService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/clock-in")
    public ResponseEntity<AttendanceModel> clockIn(@RequestBody AttendanceDTO request) {
        AttendanceModel attendance = attendanceService.clockIn(request);
        return ResponseEntity.ok(attendance);
    }

    @PostMapping("/clock-out")
    public ResponseEntity<?> clockOut(@RequestBody Map<String, Object> request) {
        try {
            // Ambil employeeId dari JSON request
            Long employeeId = Long.valueOf(request.get("employeeId").toString());

            AttendanceModel attendance = attendanceService.clockOut(employeeId);
            return ResponseEntity.ok(attendance);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getAttendanceSummary(@RequestParam Long employeeId,
                                                  @RequestParam int month,
                                                  @RequestParam int year) {
        Optional<AttendanceSummaryModel> summary = attendanceService.getAttendanceSummary(employeeId, month, year);

        return summary.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body((AttendanceSummaryModel) Map.of("message", "Data not found")));
    }

}
