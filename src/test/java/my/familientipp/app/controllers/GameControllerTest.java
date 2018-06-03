package my.familientipp.app.controllers;

import my.familientipp.app.models.Game;
import my.familientipp.app.services.GameServiceImpl;
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

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    private static final String PATH = "/spiele";
    private static final String GAMES_VIEW = "games";
    private static final String GAMES_ATTRIBUTE = "games";

    @Mock
    private GameServiceImpl gameService;

    @InjectMocks
    private GameController controller;

    private MockMvc mockMvc;


    @Before
    public void setUp() {
        controller = new GameController(gameService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getIsSuccessfulAndCorrectViewIsReturned() throws Exception {
        mockMvc.perform(get(PATH))
                .andExpect(status().isOk())
                .andExpect(view().name(GAMES_VIEW));
    }

    @Test
    public void responseContainsAllGames() throws Exception {
        Game game1 = new Game();
        Game game2 = new Game();
        List<Game> allGames = Arrays.asList(game1,game2);
        when(gameService.findAll()).thenReturn(allGames);

        mockMvc.perform(get(PATH))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(GAMES_ATTRIBUTE))
                .andExpect(model().attribute(GAMES_ATTRIBUTE,containsInAnyOrder(allGames.toArray())));
    }
}
