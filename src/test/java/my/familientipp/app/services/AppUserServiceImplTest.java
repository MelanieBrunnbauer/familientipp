package my.familientipp.app.services;

import my.familientipp.app.controllers.AppUserBuilder;
import my.familientipp.app.models.AppUser;
import my.familientipp.app.repositories.AppUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static my.familientipp.app.setup.TestConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppUserServiceImplTest {


    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserServiceImpl appUserService;
    private AppUser appUser1;
    private AppUser appUser2;

    @Before
    public void setUp() {
        appUserService = new AppUserServiceImpl(appUserRepository);
        appUser1 = new AppUserBuilder()
                    .withFirstName(FIRST_NAME_MAX)
                    .withLastName(LAST_NAME_MUSTERMANN)
                    .build();
        appUser2 = new AppUserBuilder()
                    .withFirstName(FIRST_NAME_ANNA)
                    .withLastName(LAST_NAME_SCHMID)
                    .build();
    }

    @Test
    public void returnsAllAppUsers() {

        List<AppUser> allAppUsers = Arrays.asList(appUser1, appUser2);
        when(appUserRepository.findAll()).thenReturn(allAppUsers);

        List<AppUser> result = appUserService.findAll();
        assertThat(result,containsInAnyOrder(allAppUsers.toArray()));
    }

    @Test
    public void returnsAppUserByFirstName() {
        when(appUserRepository.findByFirstName(anyString())).thenReturn(appUser1);
        AppUser appUser = appUserService.findByFirstName(FIRST_NAME_MAX);
        assertThat(appUser.getFirstName(),is(FIRST_NAME_MAX));
    }

    @Test
    public void persistsAppUser() {
        appUserService.persist(appUser1);
        verify(appUserRepository,times(1)).save(appUser1);
    }
}