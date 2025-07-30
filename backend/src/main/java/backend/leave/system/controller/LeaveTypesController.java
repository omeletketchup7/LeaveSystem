package backend.leave.system.controller;

import backend.leave.system.entity.LeaveTypesEntity;
import backend.leave.system.repository.LeaveTypesRepo;
import backend.leave.system.service.LeaveTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveTypesController {

    @Autowired
    LeaveTypesService leaveTypesService;

    @GetMapping("/get-type")
    public List<LeaveTypesEntity> getLeaveTypes() {
        return leaveTypesService.getType();
    }
}
