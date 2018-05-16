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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static my.familientipp.app.setup.TestConstants.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WinnerTipControllerTest {

    private static final String URL_TEMPLATE = "/siegertipp";
    private static final String VIEW_NAME = "winnertip";
    private static final String WINNER_TIPS_ATTRIBUTE = "winnertips";
    private static final String SOCCER_TEAM_ATTRIBUTE = "soccerTeams";
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
    public void requestIsSuccessfulAndCorrectViewIsReturned() throws Exception {
        mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME));
    }

    @Test
    public void responseContainsAllWinnerTips() throws Exception {
        verifyAttribute(WINNER_TIPS_ATTRIBUTE)
                .andExpect(model().attribute(WINNER_TIPS_ATTRIBUTE, containsInAnyOrder(winnerTips.toArray())))
                .andExpect(model().attribute(WINNER_TIPS_ATTRIBUTE, hasItem(
                            allOf(
                                    hasProperty(FIRST_NAME, is(USER_FIRST_NAME_1)),
                                    hasProperty(FIFA_CODE, is(FIFA_CODE_TEAM_1))
                            ))))
                .andExpect(model().attribute(WINNER_TIPS_ATTRIBUTE, hasItem(
                            allOf(
                                    hasProperty(FIRST_NAME, is(USER_FIRST_NAME_2)),
                                    hasProperty(FIFA_CODE, is(FIFA_CODE_TEAM_2))
                            ))));

        verify(winnerTipService,times(1)).getAllWinnertips();
    }

    @Test
    public void responseContainsAllSoccerTeams() throws Exception {
        verifyAttribute(SOCCER_TEAM_ATTRIBUTE)
                .andExpect(model().attribute(SOCCER_TEAM_ATTRIBUTE,containsInAnyOrder(soccerTeams.toArray())));

        verify(winnerTipService,times(1)).getAllSoccerTeams();
    }

    private ResultActions verifyAttribute(String winnerTips) throws Exception {
        return mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(model().attributeExists(winnerTips));
    }


    private List<WinnerTipDTO> setupWinnertips() {
        WinnerTipDTO firstWinnertip = new WinnerTipDTO(USER_FIRST_NAME_1,FIFA_CODE_TEAM_1);
        WinnerTipDTO secondWinnertip = new WinnerTipDTO(USER_FIRST_NAME_2,FIFA_CODE_TEAM_2);

        return Arrays.asList(firstWinnertip, secondWinnertip);
    }

    private List<SoccerTeamDTO> setupSoccerTeams() {
        SoccerTeamDTO soccerTeam1 = new SoccerTeamDTO(FIFA_CODE_TEAM_1, COUNTRY_TEAM_1);
        SoccerTeamDTO soccerTeam2 = new SoccerTeamDTO(FIFA_CODE_TEAM_2, COUNTRY_TEAM_2);

        return Arrays.asList(soccerTeam1, soccerTeam2);
    }

}