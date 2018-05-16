package my.familientipp.app.services;

import my.familientipp.app.models.SoccerTeam;
import my.familientipp.app.repositories.SoccerTeamRepository;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SoccerTeamServiceTest {

    @Mock
    private SoccerTeamRepository repository;

    @InjectMocks
    private SoccerTeamService service;

    private List<SoccerTeam> soccerTeams;


    @Before
    public void setUp() {
        service = new SoccerTeamService(repository);

        soccerTeams = setupSoccerTeams();
    }


    @Test
    public void returnsAllSoccerTeams() {
        when(repository.findAll()).thenReturn(soccerTeams);
        List<SoccerTeam> result = service.findAllSoccerTeams();
        assertThat(result, containsInAnyOrder(soccerTeams.toArray()));
    }

    private List<SoccerTeam> setupSoccerTeams() {
        SoccerTeam soccerTeam1 = new SoccerTeam(FIFA_CODE_TEAM_1, COUNTRY_TEAM_1);
        SoccerTeam soccerTeam2 = new SoccerTeam(FIFA_CODE_TEAM_2, COUNTRY_TEAM_2);
        return Arrays.asList(soccerTeam1, soccerTeam2);
    }
}
