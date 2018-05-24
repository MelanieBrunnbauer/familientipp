package my.familientipp.app.controllers;

import my.familientipp.app.DTO.SoccerTeamDTO;
import my.familientipp.app.DTO.WinnerTipDTO;
import my.familientipp.app.services.WinnerTipService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static my.familientipp.app.setup.TestConstants.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WinnerTipControllerTest {

    private static final String URL_TEMPLATE = "/siegertipp";
    private static final String URL_TEMPLATE_EDIT = URL_TEMPLATE + "/edit/" + FIRST_NAME_MAX;
    private static final String VIEW_NAME = "winnertip";
    private static final String EDIT_VIEW_NAME = "editWinnertip";
    private static final String WINNER_TIPS_ATTRIBUTE = "winnertips";
    private static final String APP_USER_ATTRIBUTE = "appUser";
    private static final String SOCCER_TEAMS_ATTRIBUTE = "soccerTeams";
    private static final String WINNER_TIP_ATTRIBUTE = "winnertip";
    private static final String FIRST_NAME = "firstNameOfAppUser";
    private static final String FIFA_CODE = "fifaCodeOfSoccerTeam";

    @Mock
    private WinnerTipService winnerTipService;


    @InjectMocks
    private WinnerTipController winnerTipController;

    private MockMvc mockMvc;
    private List<WinnerTipDTO> winnerTips;
    private List<SoccerTeamDTO> soccerTeams;


    @Before
    public void setUp() {
        winnerTipController = new WinnerTipController(winnerTipService);
        mockMvc = MockMvcBuilders.standaloneSetup(winnerTipController).build();

        winnerTips = setupWinnertips();
        soccerTeams = setupSoccerTeams();
        when(winnerTipService.getAllWinnertips()).thenReturn(winnerTips);
        when(winnerTipService.getAllSoccerTeams()).thenReturn(soccerTeams);
    }


    @Test
    public void getIsSuccessfulAndCorrectViewIsReturned() throws Exception {
        mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME));
    }

    @Test
    public void responseContainsAllWinnerTips() throws Exception {
        mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(model().attributeExists(WINNER_TIPS_ATTRIBUTE))
                .andExpect(model().attribute(WINNER_TIPS_ATTRIBUTE, containsInAnyOrder(winnerTips.toArray())))
                .andExpect(model().attribute(WINNER_TIPS_ATTRIBUTE, hasItem(
                        allOf(
                                hasProperty(FIRST_NAME, is(FIRST_NAME_MAX)),
                                hasProperty(FIFA_CODE, is(FIFA_CODE_RUSSLAND))
                        ))))
                .andExpect(model().attribute(WINNER_TIPS_ATTRIBUTE, hasItem(
                        allOf(
                                hasProperty(FIRST_NAME, is(FIRST_NAME_ANNA)),
                                hasProperty(FIFA_CODE, is(FIFA_CODE_DEUTSCHLAND))
                        ))));

        verify(winnerTipService, times(1)).getAllWinnertips();
    }

    @Test
    public void getEditIsSuccessfulAndCorrectViewIsReturned() throws Exception {
        mockMvc.perform(get(URL_TEMPLATE_EDIT))
                .andExpect(status().isOk())
                .andExpect(view().name(EDIT_VIEW_NAME))
                //  .andExpect(model().attribute(SOCCER_TEAMS_ATTRIBUTE,containsInAnyOrder(soccerTeams.toArray())))
                .andExpect(model().attribute(APP_USER_ATTRIBUTE, is(FIRST_NAME_MAX)))
                .andExpect(model().attributeExists(WINNER_TIP_ATTRIBUTE));
        verify(winnerTipService, times(1)).getAllSoccerTeams();
    }

    @Test
    public void postEditRedirects() throws Exception {
        mockMvc.perform(post(URL_TEMPLATE_EDIT))
                .andExpect(redirectedUrl(URL_TEMPLATE))
                .andExpect(status().isFound());
        verify(winnerTipService, times(1)).persistEdited(any(WinnerTipDTO.class));

    }

    private List<WinnerTipDTO> setupWinnertips() {
        WinnerTipDTO firstWinnertip = new WinnerTipDTO(FIRST_NAME_MAX, FIFA_CODE_RUSSLAND);
        WinnerTipDTO secondWinnertip = new WinnerTipDTO(FIRST_NAME_ANNA, FIFA_CODE_DEUTSCHLAND);

        return Arrays.asList(firstWinnertip, secondWinnertip);
    }

    private List<SoccerTeamDTO> setupSoccerTeams() {
        SoccerTeamDTO soccerTeam1 = new SoccerTeamDTO(FIFA_CODE_RUSSLAND, COUNTRY_RUSSLAND);
        SoccerTeamDTO soccerTeam2 = new SoccerTeamDTO(FIFA_CODE_DEUTSCHLAND, COUNTRY_DEUTSCHLAND);

        return Arrays.asList(soccerTeam1, soccerTeam2);
    }

}