package web.repos;

import org.springframework.data.repository.CrudRepository;
import web.domain.User;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
