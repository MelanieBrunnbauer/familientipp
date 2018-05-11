package my.familientipp.app.services;

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppUserServiceTest {


    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserService appUserService;

    @Before
    public void setUp() {
        appUserService = new AppUserService(appUserRepository);
    }

    @Test
    public void returnsAllAppUsers() {

        List<AppUser> allAppUsers = Arrays.asList(new AppUser(), new AppUser());
        when(appUserRepository.findAll()).thenReturn(allAppUsers);

        List<AppUser> result = appUserService.findAll();
        assertThat(result,containsInAnyOrder(allAppUsers.toArray()));
    }

}