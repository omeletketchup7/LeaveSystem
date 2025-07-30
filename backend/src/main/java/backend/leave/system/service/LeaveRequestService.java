package backend.leave.system.service;

import backend.leave.system.DTO.LeaveRequestDTO;
import backend.leave.system.DTO.SendLeaveRequestDTO;
import backend.leave.system.entity.LeaveBalancesEntity;
import backend.leave.system.entity.LeaveRequestEntity;
import backend.leave.system.entity.UsersEntity;
import backend.leave.system.repository.LeaveRequestRepo;
import backend.leave.system.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepo leaveRequestRepo;

    @Autowired
    private UsersService usersService;

    @Autowired
    private LeaveBalancesService leaveBalancesService;

    public List<LeaveRequestEntity> getAllRequests() {
        return leaveRequestRepo.findAll();
    }
    public List<LeaveRequestEntity> getRequestsByUserID(String userID) {
        return leaveRequestRepo.findByUserId(userID);
    }

    public int amountDay( long timeDiff) {
        int amountDays = ((int) TimeUnit.MILLISECONDS.toDays(timeDiff)) + 1;
        return  (amountDays);
    }

    public List<SendLeaveRequestDTO> getWaitingRequests() {
        return leaveRequestRepo.findByStatus("waiting").stream().map(leaveRequestEntity -> {
            SendLeaveRequestDTO sendLeaveRequestDTO = new SendLeaveRequestDTO();
            sendLeaveRequestDTO.setLeaveTypeId(leaveRequestEntity.getLeaveTypeId());
            sendLeaveRequestDTO.setUserId(leaveRequestEntity.getUserId());
            sendLeaveRequestDTO.setStartDate(leaveRequestEntity.getStartDate());
            sendLeaveRequestDTO.setEndDate(leaveRequestEntity.getEndDate());
            sendLeaveRequestDTO.setReason(leaveRequestEntity.getReason());
            sendLeaveRequestDTO.setStatus(leaveRequestEntity.getStatus());
            sendLeaveRequestDTO.setId(leaveRequestEntity.getId());
            sendLeaveRequestDTO.setCreatedAt(leaveRequestEntity.getCreatedAt());

            UsersEntity usersEntity = usersService.getUserById(leaveRequestEntity.getUserId());
            sendLeaveRequestDTO.setUsername(usersEntity.getUsername());
            sendLeaveRequestDTO.setUserDepartment(usersEntity.getDepartment());

            sendLeaveRequestDTO.setAmountDays(amountDay(leaveRequestEntity.getEndDate().getTime() - leaveRequestEntity.getStartDate().getTime()));
            return sendLeaveRequestDTO;
        }).collect(Collectors.toList());
    }


    public List<SendLeaveRequestDTO> getWaitingRequestsByUserId(String userID) {
        return leaveRequestRepo.findByStatusAndUserId("waiting",userID).stream().map(leaveRequestEntity -> {
            SendLeaveRequestDTO sendLeaveRequestDTO = new SendLeaveRequestDTO();
            sendLeaveRequestDTO.setLeaveTypeId(leaveRequestEntity.getLeaveTypeId());
            sendLeaveRequestDTO.setUserId(leaveRequestEntity.getUserId());
            sendLeaveRequestDTO.setStartDate(leaveRequestEntity.getStartDate());
            sendLeaveRequestDTO.setEndDate(leaveRequestEntity.getEndDate());
            sendLeaveRequestDTO.setReason(leaveRequestEntity.getReason());
            sendLeaveRequestDTO.setStatus(leaveRequestEntity.getStatus());
            sendLeaveRequestDTO.setId(leaveRequestEntity.getId());
            sendLeaveRequestDTO.setCreatedAt(leaveRequestEntity.getCreatedAt());

            UsersEntity usersEntity = usersService.getUserById(leaveRequestEntity.getUserId());
            sendLeaveRequestDTO.setUsername(usersEntity.getUsername());
            sendLeaveRequestDTO.setUserDepartment(usersEntity.getDepartment());

            sendLeaveRequestDTO.setAmountDays(amountDay(leaveRequestEntity.getEndDate().getTime() - leaveRequestEntity.getStartDate().getTime()));
            return sendLeaveRequestDTO;
        }).collect(Collectors.toList());
    }


    public void approveRequest(String id, String commentRequest) {
        LeaveRequestEntity leaveRequestEntity =  leaveRequestRepo.findById(id).orElse(null);
        LeaveBalancesEntity leaveBalancesEntity = new LeaveBalancesEntity();
        if (leaveRequestEntity != null) {
            if(leaveBalancesService.check(leaveRequestEntity.getUserId(),leaveRequestEntity.getCreatedAt().getYear(),leaveRequestEntity.getLeaveTypeId())) {
                String balanceId = leaveBalancesService.getIdByCheck(leaveRequestEntity.getUserId(),leaveRequestEntity.getCreatedAt().getYear(),leaveRequestEntity.getLeaveTypeId());
                LeaveBalancesEntity oldLeaveBalances = leaveBalancesService.getBalanceId(balanceId);
                int remainingDays = oldLeaveBalances.getRemainingDays();
                int leaveDays = amountDay(leaveRequestEntity.getEndDate().getTime() - leaveRequestEntity.getStartDate().getTime());
                oldLeaveBalances.setRemainingDays(remainingDays - leaveDays);
                leaveBalancesService.save(oldLeaveBalances);
            }else{
                String balanceId;
                do {
                    String idUUID = UUID.randomUUID().toString().replaceAll("[^0-9]", "");
                    balanceId = "balanceId" + idUUID.substring(0, 6);
                } while (leaveBalancesService.checkId(balanceId));
                leaveBalancesEntity.setId(balanceId);
                leaveBalancesEntity.setUserId(leaveRequestEntity.getUserId());
                leaveBalancesEntity.setLeaveTypeId(leaveRequestEntity.getLeaveTypeId());
                leaveBalancesEntity.setYearBalance(leaveRequestEntity.getCreatedAt().getYear());
                if(leaveRequestEntity.getLeaveTypeId().equals("sick_leave")) {
                    leaveBalancesEntity.setRemainingDays(30 - amountDay(leaveRequestEntity.getEndDate().getTime() - leaveRequestEntity.getStartDate().getTime()) );
                } else if (leaveRequestEntity.getLeaveTypeId().equals("personal_leave")) {
                    leaveBalancesEntity.setRemainingDays(10 - amountDay(leaveRequestEntity.getEndDate().getTime() - leaveRequestEntity.getStartDate().getTime()) );
                } else if (leaveRequestEntity.getLeaveTypeId().equals("vacation_leave")) {
                    leaveBalancesEntity.setRemainingDays(10 - amountDay(leaveRequestEntity.getEndDate().getTime() - leaveRequestEntity.getStartDate().getTime()) );
                }
                leaveBalancesService.save(leaveBalancesEntity);
            }
            leaveRequestEntity.setCommentRequest(commentRequest);
            leaveRequestEntity.setStatus("approved");
            leaveRequestRepo.save(leaveRequestEntity);
        }
    }

    public void rejectRequest(String id,String  commentRequest) {
        LeaveRequestEntity leaveRequestEntity =  leaveRequestRepo.findById(id).orElse(null);
        if (leaveRequestEntity != null) {
            leaveRequestEntity.setCommentRequest(commentRequest);
            leaveRequestEntity.setStatus("rejected");
            leaveRequestRepo.save(leaveRequestEntity);
        }
    }

    public LeaveRequestEntity insertRequest(LeaveRequestDTO request) {
        LeaveRequestEntity leaveRequestEntity = new LeaveRequestEntity();
        if (request.getId() == null) {
            String id;
            do {
                String idUUID = UUID.randomUUID().toString().replaceAll("[^0-9]", "");
                id = "requestId" + idUUID.substring(0, 6);
            } while (leaveRequestRepo.existsById(id));
            leaveRequestEntity.setId(id);
            leaveRequestEntity.setCreatedAtNow();
        }else {
            leaveRequestEntity.setId(request.getId());
        }
        leaveRequestEntity.setUserId(request.getUserId());
        leaveRequestEntity.setLeaveTypeId(request.getLeaveTypeId());
        leaveRequestEntity.setStartDate(request.getStartDate());
        leaveRequestEntity.setEndDate(request.getEndDate());
        leaveRequestEntity.setStatus("waiting");
        leaveRequestEntity.setReason(request.getReason());
        return leaveRequestRepo.save(leaveRequestEntity);
    }

    public List<SendLeaveRequestDTO> amountDayDTO(String id) {
        return leaveRequestRepo.findByUserIdOrderByCreatedAtDesc(id).stream().map(leaveRequestEntity -> {
            SendLeaveRequestDTO sendLeaveRequestDTO = new SendLeaveRequestDTO();
            sendLeaveRequestDTO.setLeaveTypeId(leaveRequestEntity.getLeaveTypeId());
            sendLeaveRequestDTO.setUserId(leaveRequestEntity.getUserId());
            sendLeaveRequestDTO.setStartDate(leaveRequestEntity.getStartDate());
            sendLeaveRequestDTO.setEndDate(leaveRequestEntity.getEndDate());
            sendLeaveRequestDTO.setReason(leaveRequestEntity.getReason());
            sendLeaveRequestDTO.setStatus(leaveRequestEntity.getStatus());
            sendLeaveRequestDTO.setId(leaveRequestEntity.getId());
            sendLeaveRequestDTO.setCreatedAt(leaveRequestEntity.getCreatedAt());

            UsersEntity usersEntity = usersService.getUserById(leaveRequestEntity.getUserId());
            sendLeaveRequestDTO.setUsername(usersEntity.getUsername());
            sendLeaveRequestDTO.setUserDepartment(usersEntity.getDepartment());

            sendLeaveRequestDTO.setAmountDays(amountDay(leaveRequestEntity.getEndDate().getTime() - leaveRequestEntity.getStartDate().getTime()));
            return sendLeaveRequestDTO;
        }).collect(Collectors.toList());
    }

}
