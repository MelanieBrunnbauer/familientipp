package my.familientipp.app.controllers;

import my.familientipp.app.models.AppUser;

public class AppUserBuilder {

    private String firstName="";
    private String lastName="";

    public AppUserBuilder withFirstName(String firstName){
        this.firstName=firstName;
        return this;
    }

    public AppUserBuilder withLastName(String lastName){
        this.lastName=lastName;
        return this;
    }
    public AppUser build(){
        return new AppUser(firstName, lastName);
    }
}
