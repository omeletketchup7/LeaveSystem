package backend.leave.system.controller;

import backend.leave.system.entity.LeaveBalancesEntity;
import backend.leave.system.repository.LeaveBalancesRepo;
import backend.leave.system.service.LeaveBalancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveBalanceController {

    @Autowired
    LeaveBalancesService leaveBalancesService;

    @GetMapping("/get-balance")
    public List<LeaveBalancesEntity> getAll(){
        return leaveBalancesService.getAll();
    }

    @GetMapping("/get-remainDay")
    public int getRemainDay(@RequestParam String id){
        return leaveBalancesService.getRemainDay(id);
    }

    @GetMapping("/get-leaveDayUsed")
    public int getleaveDayUsed(@RequestParam String id){
        return leaveBalancesService.getleaveDayUsed(id);
    }
}
