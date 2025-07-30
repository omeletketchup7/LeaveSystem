package backend.leave.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leave_balances")
public class LeaveBalancesEntity {
    @Id
    @Column(length = 50)
    private String id;

    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "leave_type_id", nullable = false, length = 50)
    private String leaveTypeId;

    @Column(name = "year_balance", nullable = false)
    private int yearBalance;

    @Column(name = "remaining_days", nullable = false)
    private int remainingDays;

    public LeaveBalancesEntity() {}

    public LeaveBalancesEntity(String id, String userId, String leaveTypeId, int yearBalance, int remainingDays) {
        this.id = id;
        this.userId = userId;
        this.leaveTypeId = leaveTypeId;
        this.yearBalance = yearBalance;
        this.remainingDays = remainingDays;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(String leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public int getYearBalance() {
        return yearBalance;
    }

    public void setYearBalance(int yearBalance) {
        this.yearBalance = yearBalance;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }
}
