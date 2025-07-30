package backend.leave.system.repository;

import backend.leave.system.entity.LeaveTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LeaveTypesRepo extends JpaRepository<LeaveTypesEntity, String> {
    @Query("SELECT l.maxDays FROM LeaveTypesEntity l WHERE l.id = :typeId")
    Integer getMaxDayById(@Param("typeId") String typeId);
}
