package my.familientipp.app.controllers;

import my.familientipp.app.models.AppUser;
import my.familientipp.app.services.AppUserService;
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
    private static final String APP_USERS_ATTRIBUTE = "appUsers";
    private static final String ATTRIBUTE_FIRST_NAME = "firstName";
    private static final String ATTRIBUTE_LAST_NAME = "lastName";
    private static final String MUSTERMANN = "Mustermann";
    private static final String MAX = "Max";
    private static final String ANNA = "Anna";
    private static final String MUSTERFRAU = "Musterfrau";

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

        appUsers = setupAppUsers();
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
                .andExpect(model().attribute(APP_USERS_ATTRIBUTE, containsInAnyOrder(appUsers.toArray())))
                .andExpect(model().attribute(APP_USERS_ATTRIBUTE, hasItem(
                            allOf(
                                    hasProperty(ATTRIBUTE_FIRST_NAME, is(MAX)),
                                    hasProperty(ATTRIBUTE_LAST_NAME, is(MUSTERMANN))
                            ))))
                .andExpect(model().attribute(APP_USERS_ATTRIBUTE, hasItem(
                            allOf(
                                    hasProperty(ATTRIBUTE_FIRST_NAME, is(ANNA)),
                                    hasProperty(ATTRIBUTE_LAST_NAME, is(MUSTERFRAU))
                            ))));

        verify(appUserService,times(1)).findAll();
    }

    private List<AppUser> setupAppUsers() {
        AppUser appUser1 = new AppUserBuilder()
                                .withFirstName(MAX)
                                .withLastName(MUSTERMANN)
                                .build();

        AppUser appUser2 = new AppUserBuilder()
                                .withFirstName(ANNA)
                                .withLastName(MUSTERFRAU)
                                .build();

        return Arrays.asList(appUser1, appUser2);
    }

    private ResultActions verifyAppUserAttributeExists() throws Exception {
        return mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(model().attributeExists(APP_USERS_ATTRIBUTE));
    }
}