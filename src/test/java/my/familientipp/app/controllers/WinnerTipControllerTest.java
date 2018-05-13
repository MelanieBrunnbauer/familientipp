package my.familientipp.app.controllers;

import my.familientipp.app.models.AppUser;
import my.familientipp.app.services.AppUserService;
import my.familientipp.app.setup.TestUtil;
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

import java.util.List;

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
    private static final String APP_USERS = "appUsers";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";

    @Mock
    private AppUserService appUserService;

    @InjectMocks
    private WinnerTipController winnerTipController;

    private MockMvc mockMvc;
    private List<AppUser> appUsers;


    @Before
    public void setUp() {
        winnerTipController = new WinnerTipController(appUserService);
        mockMvc = MockMvcBuilders.standaloneSetup(winnerTipController).build();

        appUsers = TestUtil.setupAppUsers();
        when(appUserService.findAll()).thenReturn(appUsers);
    }


    @Test
    public void requestIsSuccessfulAndCorrectViewIsReturned() throws Exception {
        mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME));
    }

    @Test
    public void responseContainsAllAppUsers() throws Exception {
        verifyAppUserAttributeExists()
                .andExpect(model().attribute(APP_USERS, containsInAnyOrder(appUsers.toArray())))
                .andExpect(model().attribute(APP_USERS, hasItem(
                            allOf(
                                    hasProperty(FIRST_NAME, is(TestUtil.MAX)),
                                    hasProperty(LAST_NAME, is(TestUtil.MUSTERMANN))
                            ))))
                .andExpect(model().attribute(APP_USERS, hasItem(
                            allOf(
                                    hasProperty(FIRST_NAME, is(TestUtil.ANNA)),
                                    hasProperty(LAST_NAME, is(TestUtil.MUSTERFRAU))
                            ))));

        verify(appUserService,times(1)).findAll();
    }



    private ResultActions verifyAppUserAttributeExists() throws Exception {
        return mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(model().attributeExists(APP_USERS));
    }
}