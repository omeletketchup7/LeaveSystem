package backend.leave.system.service;

import backend.leave.system.entity.LeaveTypesEntity;
import backend.leave.system.repository.LeaveTypesRepo;
import backend.leave.system.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveTypesService {

    @Autowired
    private LeaveTypesRepo leaveTypesRepo;

    public List<LeaveTypesEntity> getType() {
        return leaveTypesRepo.findAll();
    }

    public int getMaxDayByTypeId(String typeId) {
        return leaveTypesRepo.getMaxDayById(typeId);
    }
}
