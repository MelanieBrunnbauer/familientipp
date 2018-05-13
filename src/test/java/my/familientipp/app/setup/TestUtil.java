package my.familientipp.app.setup;

import my.familientipp.app.controllers.AppUserBuilder;
import my.familientipp.app.models.AppUser;

import java.util.Arrays;
import java.util.List;

public class TestUtil {

    public static final String MAX = "Max";
    public static final String MUSTERMANN = "Mustermann";
    public static final String ANNA = "Anna";
    public static final String MUSTERFRAU = "Musterfrau";


    public static List<AppUser> setupAppUsers() {
        AppUser appUser1 = new AppUserBuilder()
                .withFirstName(MAX)
                .withLastName(MUSTERMANN)
                .build();

        AppUser appUser2 = new AppUserBuilder()
                .withFirstName(ANNA)
                .withLastName(MUSTERFRAU)
                .build();

        return Arrays.asList(appUser1, appUser2);
    }
}
