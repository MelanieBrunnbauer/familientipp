package my.familientipp.app.services;

import my.familientipp.app.models.AppUser;
import my.familientipp.app.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository repository;

    @Autowired
    AppUserServiceImpl(AppUserRepository repository) {
        this.repository = repository;
    }

    public List<AppUser> findAll() {
        return repository.findAll();
    }

    public AppUser findByFirstName(String firstNameOfAppUser) {
        return repository.findByFirstName(firstNameOfAppUser);
    }

    public void persist(AppUser appUser) {
        repository.save(appUser);
    }


}
