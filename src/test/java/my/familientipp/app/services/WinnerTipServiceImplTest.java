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
    private AppUser appUser2;
    private AppUser appUser1;
    private SoccerTeam soccerTeam1;
    private SoccerTeam soccerTeam2;

    @Before
    public void setUp() {
        winnerTipService = new WinnerTipServiceImpl(appUserService, soccerTeamService);

        appUsers = setupAppUsersWithWinnertips();
        when(appUserService.findAll()).thenReturn(appUsers);

        allSoccerTeams = setupSoccerTeams();
        when(soccerTeamService.findAll()).thenReturn(allSoccerTeams);
    }

    @Test
    public void correctDatainWinnertipDTOs() {

        List<WinnerTipDTO> winnerTips = winnerTipService.getAllWinnertips();

        assertThat(winnerTips, hasSize(appUsers.size()));

        WinnerTipDTO first = winnerTips.get(0);
        assertThat(first.getFifaCodeOfSoccerTeam(),is(FIFA_CODE_TEAM_1));
        assertThat(first.getFirstNameOfAppUser(),is(USER_FIRST_NAME_1));

        WinnerTipDTO second = winnerTips.get(1);
        assertThat(second.getFifaCodeOfSoccerTeam(),is(FIFA_CODE_TEAM_2));
        assertThat(second.getFirstNameOfAppUser(),is(USER_FIRST_NAME_2));

    }

    @Test
    public void leerForMissingWinnertip() {
        appUsers = setupAppUsersSecondWithoutWinnertip();
        when(appUserService.findAll()).thenReturn(appUsers);

        List<WinnerTipDTO> winnerTips = winnerTipService.getAllWinnertips();
        assertThat(winnerTips.get(1).getFifaCodeOfSoccerTeam(),is("leer"));
    }

    @Test
    public void correctDataInSoccerTeamDTOs() {
        List<SoccerTeamDTO> result = winnerTipService.getAllSoccerTeams();

        assertThat(result,hasSize(allSoccerTeams.size()));

        SoccerTeamDTO first = result.get(0);
        assertThat(first.getFifaCode(),is(FIFA_CODE_TEAM_1));
        assertThat(first.getCountry(),is(COUNTRY_TEAM_1));

        SoccerTeamDTO second = result.get(1);
        assertThat(second.getFifaCode(),is(FIFA_CODE_TEAM_2));
        assertThat(second.getCountry(),is(COUNTRY_TEAM_2));
    }

    @Test
    public void persistEditedWinnertip() {
        WinnerTipDTO editedWinnertip = new WinnerTipDTO(USER_FIRST_NAME_1,FIFA_CODE_TEAM_2);

        when(appUserService.findByFirstName(USER_FIRST_NAME_1)).thenReturn(appUser1);
        when(soccerTeamService.findByFIFACode(FIFA_CODE_TEAM_2)).thenReturn(soccerTeam2);

        winnerTipService.persistEdited(editedWinnertip);

        verify(appUserService,times(1)).persist(appUserCaptor.capture());
        assertThat(appUserCaptor.getValue().getFirstName(),is(USER_FIRST_NAME_1));
        assertThat(appUserCaptor.getValue().getWinnertip(),is(Optional.of(soccerTeam2)));
    }

    private List<SoccerTeam> setupSoccerTeams() {
        soccerTeam1 = new SoccerTeam(FIFA_CODE_TEAM_1, COUNTRY_TEAM_1);
        soccerTeam2 = new SoccerTeam(FIFA_CODE_TEAM_2, COUNTRY_TEAM_2);
        return Arrays.asList(soccerTeam1, soccerTeam2);
    }

    private List<AppUser> setupAppUsersWithWinnertips() {
        appUser1 = new AppUserBuilder()
                .withFirstName(USER_FIRST_NAME_1)
                .withLastName(USER_LAST_NAME_1)
                .withWinnerTip(new SoccerTeam(FIFA_CODE_TEAM_1,COUNTRY_TEAM_1))
                .build();

        appUser2 = new AppUserBuilder()
                .withFirstName(USER_FIRST_NAME_2)
                .withLastName(USER_LAST_NAME_2)
                .withWinnerTip(new SoccerTeam(FIFA_CODE_TEAM_2,COUNTRY_TEAM_2))
                .build();

        return Arrays.asList(appUser1, appUser2);
    }

    private List<AppUser> setupAppUsersSecondWithoutWinnertip() {
        appUser1 = new AppUserBuilder()
                .withFirstName(USER_FIRST_NAME_1)
                .withLastName(USER_LAST_NAME_1)
                .withWinnerTip(new SoccerTeam(FIFA_CODE_TEAM_1,COUNTRY_TEAM_1))
                .build();

        appUser2 = new AppUserBuilder()
                .withFirstName(USER_FIRST_NAME_2)
                .withLastName(USER_LAST_NAME_2)
                .build();

        return Arrays.asList(appUser1, appUser2);
    }
}