package site.satriiadaffa.hcis.app.repository.attendance;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.satriiadaffa.hcis.app.model.attendance.AttendanceModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceModel, Long> {

    @Query("SELECT a FROM AttendanceModel a WHERE a.employeeId = :employeeId " +
            "AND a.clockInTime BETWEEN :startOfDay AND :endOfDay")
    Optional<AttendanceModel> findByEmployeeIdAndDate(
            @Param("employeeId") Long employeeId,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay);

    @Query("SELECT a FROM AttendanceModel a WHERE a.employeeId = :employeeId ORDER BY a.clockInTime DESC")
    Optional<AttendanceModel> findLatestByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT COUNT(a) FROM AttendanceModel a WHERE a.employeeId = :employeeId AND MONTH(a.clockInTime) = :month AND YEAR(a.clockInTime) = :year AND a.status = 'Present'")
    int countDays(@Param("employeeId") Long employeeId, @Param("month") int month, @Param("year") int year, @Param("status") String status);

    @Query("SELECT COALESCE(SUM(a.workHours), 0) FROM AttendanceModel a WHERE a.employeeId = :employeeId AND MONTH(a.clockInTime) = :month AND YEAR(a.clockInTime) = :year")
    double sumWorkHours(@Param("employeeId") Long employeeId, @Param("month") int month, @Param("year") int year);

    Optional<Object> findByEmployeeIdAndDate(Long employeeId, LocalDate today);
}
