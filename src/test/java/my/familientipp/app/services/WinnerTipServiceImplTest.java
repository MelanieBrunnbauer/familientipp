package my.familientipp.app.services;

import my.familientipp.app.DTO.SoccerTeamDTO;
import my.familientipp.app.DTO.WinnerTipDTO;
import my.familientipp.app.controllers.AppUserBuilder;
import my.familientipp.app.models.AppUser;
import my.familientipp.app.models.SoccerTeam;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static my.familientipp.app.setup.TestConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WinnerTipServiceImplTest {

    @Mock
    private AppUserService appUserService;

    @Mock
    private SoccerTeamService soccerTeamService;

    @InjectMocks
    private WinnerTipServiceImpl winnerTipService;

    private List<AppUser> appUsers;
    private List<SoccerTeam> allSoccerTeams;

    @Captor
    private ArgumentCaptor<AppUser> appUserCaptor;
    private AppUser anna;
    private AppUser max;
    private AppUser bernd;
    private SoccerTeam soccerTeamRUS;
    private SoccerTeam soccerTeamGER;

    @Before
    public void setUp() {
        winnerTipService = new WinnerTipServiceImpl(appUserService, soccerTeamService);

        allSoccerTeams = setupSoccerTeams();
        when(soccerTeamService.findAll()).thenReturn(allSoccerTeams);

        setupAppUsers();
        appUsers = Arrays.asList(max, anna);
        when(appUserService.findAll()).thenReturn(appUsers);
    }

    @Test
    public void sortsWinnerTipsAlphabeticallyByFirstName() {
        List<AppUser> unsortedAppUsers = Arrays.asList(bernd, max, anna);
        when(appUserService.findAll()).thenReturn(unsortedAppUsers);

        List<WinnerTipDTO> allWinnertips = winnerTipService.getAllWinnertips();

        assertThat(allWinnertips.get(0).getFirstNameOfAppUser(), is(FIRST_NAME_ANNA));
        assertThat(allWinnertips.get(1).getFirstNameOfAppUser(), is(FIRST_NAME_BERND));
        assertThat(allWinnertips.get(2).getFirstNameOfAppUser(), is(FIRST_NAME_MAX));
    }

    @Test
    public void sortsSoccerTeamsAlphabeticallyByCountry() {
        List<SoccerTeam> unsortedSoccerTeams = Arrays.asList(soccerTeamRUS, soccerTeamGER);
        when(soccerTeamService.findAll()).thenReturn(unsortedSoccerTeams);

        List<SoccerTeamDTO> sortedSoccerTeams = winnerTipService.getAllSoccerTeams();

        assertThat(sortedSoccerTeams.get(0).getCountry(),is(COUNTRY_DEUTSCHLAND));
        assertThat(sortedSoccerTeams.get(1).getCountry(),is(COUNTRY_RUSSLAND));
    }

    @Test
    public void containsCorrectDataInWinnertips() {

        List<WinnerTipDTO> winnerTips = winnerTipService.getAllWinnertips();

        assertThat(winnerTips, hasSize(appUsers.size()));

        WinnerTipDTO first = winnerTips.get(0);
        assertThat(first, samePropertyValuesAs(new WinnerTipDTO(FIRST_NAME_ANNA, FIFA_CODE_DEUTSCHLAND)));

        WinnerTipDTO second = winnerTips.get(1);
        assertThat(second, samePropertyValuesAs(new WinnerTipDTO(FIRST_NAME_MAX, FIFA_CODE_RUSSLAND)));
    }

    @Test
    public void setsMessageForMissingWinnertip() {
        anna.setWinnertip(null);
        when(appUserService.findAll()).thenReturn(appUsers);

        List<WinnerTipDTO> winnerTips = winnerTipService.getAllWinnertips();

        assertThat(winnerTips.get(0).getFifaCodeOfSoccerTeam(), is("bitte Tipp abgeben"));
    }

    @Test
    public void containsCorrectDataInSoccerTeams() {
        List<SoccerTeamDTO> result = winnerTipService.getAllSoccerTeams();

        assertThat(result, hasSize(allSoccerTeams.size()));

        SoccerTeamDTO secondTeam = result.get(0);
        assertThat(secondTeam, samePropertyValuesAs(new SoccerTeamDTO(FIFA_CODE_DEUTSCHLAND, COUNTRY_DEUTSCHLAND)));

        SoccerTeamDTO firstTeam = result.get(1);
        assertThat(firstTeam, samePropertyValuesAs(new SoccerTeamDTO(FIFA_CODE_RUSSLAND, COUNTRY_RUSSLAND)));
    }

    @Test
    public void persistsEditedWinnertip() {
        WinnerTipDTO editedWinnertip = new WinnerTipDTO(FIRST_NAME_MAX, FIFA_CODE_DEUTSCHLAND);

        when(appUserService.findByFirstName(FIRST_NAME_MAX)).thenReturn(max);
        when(soccerTeamService.findByFIFACode(FIFA_CODE_DEUTSCHLAND)).thenReturn(soccerTeamGER);

        winnerTipService.persistEdited(editedWinnertip);

        verify(appUserService, times(1)).persist(appUserCaptor.capture());
        assertThat(appUserCaptor.getValue().getFirstName(), is(FIRST_NAME_MAX));
        assertThat(appUserCaptor.getValue().getWinnertip(), is(Optional.of(soccerTeamGER)));
    }

    @Test
    public void editingIsNotAllowedAfterStartTime() {

    }

    private List<SoccerTeam> setupSoccerTeams() {
        soccerTeamRUS = new SoccerTeam(FIFA_CODE_RUSSLAND, COUNTRY_RUSSLAND);
        soccerTeamGER = new SoccerTeam(FIFA_CODE_DEUTSCHLAND, COUNTRY_DEUTSCHLAND);
        return Arrays.asList(soccerTeamRUS, soccerTeamGER);
    }

    private void setupAppUsers() {
        max = new AppUserBuilder()
                .withFirstName(FIRST_NAME_MAX)
                .withLastName(LAST_NAME_MUSTERMANN)
                .withWinnerTip(soccerTeamRUS)
                .build();

        anna = new AppUserBuilder()
                .withFirstName(FIRST_NAME_ANNA)
                .withLastName(LAST_NAME_SCHMID)
                .withWinnerTip(soccerTeamGER)
                .build();

        bernd = new AppUserBuilder()
                .withFirstName(FIRST_NAME_BERND)
                .withLastName(LAST_NAME_MEIER)
                .withWinnerTip(soccerTeamRUS)
                .build();
    }

}