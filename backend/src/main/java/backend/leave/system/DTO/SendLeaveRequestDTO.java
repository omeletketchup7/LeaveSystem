package backend.leave.system.DTO;

import java.time.LocalDateTime;
import java.util.Date;

public class SendLeaveRequestDTO {

    private String id;

    private String userId;

    private String leaveTypeId;

    private Date startDate;

    private Date endDate;

    private String status;

    private String reason;

    private String commentRequest;

    private LocalDateTime createdAt;

    private String userName;

    private String userDepartment;

    private int amountDays;

    public SendLeaveRequestDTO() {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCommentRequest() {
        return commentRequest;
    }

    public void setCommentRequest(String commentRequest) {
        this.commentRequest = commentRequest;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public int getAmountDays() {
        return amountDays;
    }

    public void setAmountDays(int amountDays) {
        this.amountDays = amountDays;
    }
}

