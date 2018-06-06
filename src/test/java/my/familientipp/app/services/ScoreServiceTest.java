package my.familientipp.app.services;

import my.familientipp.app.DTO.ScoreDTO;
import my.familientipp.app.controllers.AppUserBuilder;
import my.familientipp.app.models.AppUser;
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
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScoreServiceTest {

    @Mock
    private AppUserService appUserService;

    @InjectMocks
    private ScoreService scoreService = new ScoreService(appUserService);

    private AppUser anna;
    private AppUser max;
    private AppUser bernd;
    private List<AppUser> allAppUsers;

    @Before
    public void setUp() {
        anna = new AppUserBuilder()
                .withFirstName(FIRST_NAME_ANNA)
                .withScore(10)
                .build();

        max = new AppUserBuilder()
                .withFirstName(FIRST_NAME_MAX)
                .withScore(3)
                .build();

        bernd = new AppUserBuilder()
                .withFirstName(FIRST_NAME_BERND)
                .withScore(1)
                .build();

        allAppUsers = Arrays.asList(max, bernd, anna);
    }

    @Test
    public void returnsScoresOfAllAppusers() {
        when(appUserService.findAll()).thenReturn(allAppUsers);
        List<ScoreDTO> allScores = scoreService.getSoresOfAllAppusers();
        assertThat(allScores,hasSize(3));
    }
}