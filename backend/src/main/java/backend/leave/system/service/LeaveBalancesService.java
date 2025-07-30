package backend.leave.system.service;

import backend.leave.system.DTO.LeaveBalanceDTO;
import backend.leave.system.entity.LeaveBalancesEntity;
import backend.leave.system.repository.LeaveBalancesRepo;
import backend.leave.system.repository.LeaveTypesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveBalancesService {

    @Autowired
    private LeaveBalancesRepo leaveBalancesRepo;

    @Autowired
    private LeaveTypesRepo leaveTypesRepo;
    @Autowired
    private LeaveTypesService leaveTypesService;

    public LeaveBalancesEntity insertBalance(LeaveBalancesEntity leaveBalancesEntity) {
        return leaveBalancesRepo.save(leaveBalancesEntity);
    }

    public Boolean check(String id, int yearBalance, String leaveTypeId) {
        return leaveBalancesRepo.existsByUserIdAndYearBalanceAndLeaveTypeId(id, yearBalance, leaveTypeId);
    }

    public String getIdByCheck(String id, int yearBalance, String leaveTypeId) {
        LeaveBalancesEntity balanceId = leaveBalancesRepo.findByUserIdAndLeaveTypeIdAndYearBalance(id, leaveTypeId, yearBalance);
        return balanceId.getId();
    }

    public Boolean checkId(String id) {
        return leaveBalancesRepo.existsById(id);
    }

    public LeaveBalancesEntity getBalanceId(String id) {
        return leaveBalancesRepo.findById(id).get();
    }

    public void save(LeaveBalancesEntity leaveBalancesEntity) {
        leaveBalancesRepo.save(leaveBalancesEntity);
    }

    public List<LeaveBalancesEntity> getAll() {
        return leaveBalancesRepo.findAll();
    }

    public int getRemainDay(String id) {
        int remainDay = 0;
        if (check(id, LocalDate.now().getYear(), "sick_leave")) {
            String balanceId = getIdByCheck(id, LocalDate.now().getYear(), "sick_leave");
            LeaveBalancesEntity leaveBalancesEntity = leaveBalancesRepo.findById(balanceId).get();
            remainDay += leaveBalancesEntity.getRemainingDays();
        } else {
            remainDay += (int) leaveTypesService.getMaxDayByTypeId("sick_leave");
        }

        if (check(id, LocalDate.now().getYear(), "personal_leave")) {
            String balanceId = getIdByCheck(id, LocalDate.now().getYear(), "personal_leave");
            LeaveBalancesEntity leaveBalancesEntity = leaveBalancesRepo.findById(balanceId).get();
            remainDay += leaveBalancesEntity.getRemainingDays();
        } else {
            remainDay += (int) leaveTypesService.getMaxDayByTypeId("personal_leave");
        }

        if (check(id, LocalDate.now().getYear(), "vacation_leave")) {
            String balanceId = getIdByCheck(id, LocalDate.now().getYear(), "vacation_leave");
            LeaveBalancesEntity leaveBalancesEntity = leaveBalancesRepo.findById(balanceId).get();
            remainDay += leaveBalancesEntity.getRemainingDays();
        } else {
            remainDay += (int) leaveTypesService.getMaxDayByTypeId("vacation_leave");
        }

        return remainDay;
    }

    public int getleaveDayUsed(String id) {
        int day = 0;
        if (check(id, LocalDate.now().getYear(), "sick_leave")) {
            String balanceId = getIdByCheck(id, LocalDate.now().getYear(), "sick_leave");
            LeaveBalancesEntity leaveBalancesEntity = leaveBalancesRepo.findById(balanceId).get();
            day += ((int) leaveTypesService.getMaxDayByTypeId("sick_leave")) - leaveBalancesEntity.getRemainingDays();
        }

        if (check(id, LocalDate.now().getYear(), "personal_leave")) {
            String balanceId = getIdByCheck(id, LocalDate.now().getYear(), "personal_leave");
            LeaveBalancesEntity leaveBalancesEntity = leaveBalancesRepo.findById(balanceId).get();
            day += ((int) leaveTypesService.getMaxDayByTypeId("personal_leave")) - leaveBalancesEntity.getRemainingDays();
        }

        if (check(id, LocalDate.now().getYear(), "vacation_leave")) {
            String balanceId = getIdByCheck(id, LocalDate.now().getYear(), "vacation_leave");
            LeaveBalancesEntity leaveBalancesEntity = leaveBalancesRepo.findById(balanceId).get();
            day += ((int) leaveTypesService.getMaxDayByTypeId("vacation_leave")) - leaveBalancesEntity.getRemainingDays();
        }
        return day;

    }


}
