package backend.leave.system.repository;

import backend.leave.system.entity.LeaveRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepo extends JpaRepository<LeaveRequestEntity, String> {
    List<LeaveRequestEntity> findByUserId(String userId);
    List<LeaveRequestEntity> findByUserIdOrderByCreatedAtDesc(String userId);
    List<LeaveRequestEntity> findByStatus(String status);
    List<LeaveRequestEntity> findByStatusAndUserId(String status, String userId);

}
