package my.familientipp.app.services;

import my.familientipp.app.models.AppUser;
import my.familientipp.app.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private AppUserRepository repository;

    @Autowired
    public AppUserService(AppUserRepository repository) {
        this.repository = repository;
    }

    public List<AppUser> findAll(){
        return repository.findAll();
    }

}
