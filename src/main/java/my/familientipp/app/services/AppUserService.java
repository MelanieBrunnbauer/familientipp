package my.familientipp.app.services;

import my.familientipp.app.models.AppUser;

import java.util.List;

public interface AppUserService {
    List<AppUser> findAll();

    void persist(AppUser appUser);

    AppUser findByFirstName(String firstNameOfAppUser);
}
