package backend.leave.system.controller;

import backend.leave.system.entity.UsersEntity;
import backend.leave.system.repository.UsersRepo;
import backend.leave.system.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/post-user")
    public UsersEntity insertUpdate(@RequestBody UsersEntity user) {
        return usersService.insertUser(user);
    }

    @GetMapping()
    public List<UsersEntity> getAllUsers() {
        return usersService.getAllUser();
    }

}
