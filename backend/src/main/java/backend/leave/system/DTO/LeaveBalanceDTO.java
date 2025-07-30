package backend.leave.system.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class LeaveBalanceDTO {

    private String id;

    private String userId;

    private  String username;

    private String leaveTypeId;

    private String department;

    private int sickLeaveAmount;

    private int personalLeaveAmount;

    private int vacationLeaveAmount;



    public LeaveBalanceDTO() {}



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

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSickLeaveAmount(){
        return sickLeaveAmount;
    }

    public void setSickLeaveAmount(int sickLeaveAmount){
        this.sickLeaveAmount = sickLeaveAmount;
    }


}
