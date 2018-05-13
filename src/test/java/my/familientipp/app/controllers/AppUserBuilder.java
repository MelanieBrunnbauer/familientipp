package my.familientipp.app.controllers;

import my.familientipp.app.models.AppUser;
import my.familientipp.app.models.SoccerTeam;

public class AppUserBuilder {

    private String firstName = "";
    private String lastName = "";
    private SoccerTeam winnerTip;

    public AppUserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AppUserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AppUserBuilder withWinnerTip(SoccerTeam team) {
        this.winnerTip = team;
        return this;
    }

    public AppUser build() {
        return new AppUser(firstName, lastName, winnerTip);
    }
}
