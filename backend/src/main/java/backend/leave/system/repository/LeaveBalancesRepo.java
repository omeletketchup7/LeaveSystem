package backend.leave.system.repository;

import backend.leave.system.entity.LeaveBalancesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveBalancesRepo extends JpaRepository<LeaveBalancesEntity, String> {
    boolean existsByUserIdAndYearBalanceAndLeaveTypeId(String id, int yearBalance, String leaveTypeId);
    LeaveBalancesEntity findByUserIdAndLeaveTypeIdAndYearBalance(String userId, String leaveTypeId, int yearBalance);

}
