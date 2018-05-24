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
public class SoccerTeamServiceImplTest {

    @Mock
    private SoccerTeamRepository repository;

    @InjectMocks
    private SoccerTeamServiceImpl service;

    private List<SoccerTeam> soccerTeams;


    @Before
    public void setUp() {
        service = new SoccerTeamServiceImpl(repository);

        soccerTeams = setupSoccerTeams();
    }


    @Test
    public void returnsAllSoccerTeams() {
        when(repository.findAll()).thenReturn(soccerTeams);
        List<SoccerTeam> result = service.findAll();
        assertThat(result, containsInAnyOrder(soccerTeams.toArray()));
    }

    private List<SoccerTeam> setupSoccerTeams() {
        SoccerTeam soccerTeam1 = new SoccerTeam(FIFA_CODE_RUSSLAND, COUNTRY_RUSSLAND);
        SoccerTeam soccerTeam2 = new SoccerTeam(FIFA_CODE_DEUTSCHLAND, COUNTRY_DEUTSCHLAND);
        return Arrays.asList(soccerTeam1, soccerTeam2);
    }
}
