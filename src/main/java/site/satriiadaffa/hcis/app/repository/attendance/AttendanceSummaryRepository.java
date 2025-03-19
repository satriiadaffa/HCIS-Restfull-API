package site.satriiadaffa.hcis.app.repository.attendance;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.satriiadaffa.hcis.app.model.attendance.AttendanceSummaryModel;

import java.util.Optional;

@Repository
public interface AttendanceSummaryRepository extends JpaRepository<AttendanceSummaryModel, Long> {

    @Query("SELECT a FROM AttendanceSummaryModel a WHERE a.employeeId = :employeeId AND a.month = :month AND a.year = :year")
    Optional<AttendanceSummaryModel> findByEmployeeAndMonthYear(@Param("employeeId") Long employeeId,
                                                                @Param("month") int month,
                                                                @Param("year") int year);
}
