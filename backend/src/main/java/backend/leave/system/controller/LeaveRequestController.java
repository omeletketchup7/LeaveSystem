package backend.leave.system.controller;

import backend.leave.system.DTO.LeaveRequestDTO;
import backend.leave.system.DTO.SendLeaveRequestDTO;
import backend.leave.system.entity.LeaveRequestEntity;
import backend.leave.system.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {

     @Autowired
    LeaveRequestService leaveRequestService;

     @PostMapping
     public LeaveRequestEntity saveLeaveRequest(@RequestBody LeaveRequestDTO leaveRequestEntity) {
         return leaveRequestService.insertRequest(leaveRequestEntity);
     }
     @GetMapping("/request")
     public List<LeaveRequestEntity> getLeaveRequestsByUserID( @RequestParam String userID) {
         return leaveRequestService.getRequestsByUserID(userID);
     }
    @GetMapping("/waitng-request")
    public List<SendLeaveRequestDTO> getWaitingRequests() {
        return leaveRequestService.getWaitingRequests();
    }

    @GetMapping("/waitng-request-by-userid")
    public List<SendLeaveRequestDTO> getWaitingRequestsByUserId(@RequestParam String userID) {
        return leaveRequestService.getWaitingRequestsByUserId(userID);
    }


    @PostMapping("/approve-waitng-request")
    public void approveWaitingRequest(@RequestParam String id, @RequestBody String commentRequest) {
        leaveRequestService.approveRequest(id,commentRequest);
    }

    @PostMapping("/reject-waitng-request")
    public void rejectWaitingRequest(@RequestParam String id,@RequestBody String commentRequest) {
        leaveRequestService.rejectRequest(id,commentRequest);
    }

    @GetMapping("/amount-day-dto")
    public List<SendLeaveRequestDTO> amountDayDTO(@RequestParam String id) {
         return leaveRequestService.amountDayDTO(id);
    }

}
