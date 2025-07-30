package backend.leave.system.service;

import backend.leave.system.entity.UsersEntity;
import backend.leave.system.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {

    @Autowired
    private UsersRepo usersRepo;

    public UsersEntity insertUser(UsersEntity usersEntity) {
        if (usersEntity.getId() != null) {
            Optional<UsersEntity> oldData = usersRepo.findById(usersEntity.getId());
            if(usersEntity.getDepartment() == null) {
                usersEntity.setDepartment(oldData.get().getDepartment());
            }
            if(usersEntity.getEmail() == null) {
                usersEntity.setEmail(oldData.get().getEmail());
            }
            if(usersEntity.getRole() == null) {
                usersEntity.setRole(oldData.get().getRole());
            }
            if (usersEntity.getUsername() == null) {
                usersEntity.setUsername(oldData.get().getUsername());
            }
            usersEntity.setCreatedAt(oldData.get().getCreatedAt());
            return usersRepo.save(usersEntity);
        }else{
            String id;
            do {
                String uuidNumbers = UUID.randomUUID().toString().replaceAll("[^0-9]", "");
                id = "uid" + uuidNumbers.substring(0, 6);
            } while (usersRepo.existsById(id));
            usersEntity.setId(id);
            return usersRepo.save(usersEntity);
        }
    }
    public List<UsersEntity> getAllUser() {
        return usersRepo.findAll();
    }

    public UsersEntity getUserById(String id) {
        return usersRepo.findById(id).get();
    }

}
