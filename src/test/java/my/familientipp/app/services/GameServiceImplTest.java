package my.familientipp.app.services;

import my.familientipp.app.models.Game;
import my.familientipp.app.repositories.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    private Game game1;
    private Game game2;

    @Before
    public void setUp() {
        gameService = new GameServiceImpl(gameRepository);
        game1 = new Game();
        game2 = new Game();
    }

    @Test
    public void returnsAllGames() {
        List<Game> allGames = Arrays.asList(game1, game2);
        when(gameRepository.findAll()).thenReturn(allGames);

        List<Game> result = gameService.findAll();
        assertThat(result, containsInAnyOrder(allGames.toArray()));
    }
}