package backend.leave.system.repository;

import backend.leave.system.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<UsersEntity, String> {

}
