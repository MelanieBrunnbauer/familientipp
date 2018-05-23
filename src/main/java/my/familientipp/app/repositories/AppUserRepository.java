package my.familientipp.app.repositories;

import my.familientipp.app.models.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    List<AppUser> findAll();

    AppUser findByFirstName(String firstName);
}
