package org.spring.unit.testing.presentation.user.domain.service;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.spring.unit.testing.presentation.user.SpringBaseTest;
import org.spring.unit.testing.presentation.user.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
public class UserServiceTest extends SpringBaseTest {

    UserService userService;
    User user;

    @Mock
    UserService userServiceMock = mock(UserService.class);

    @BeforeEach
    void createNewInstance(){
        user = new User();
    }

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void sampleTest(){
        String actualVal = "tet123";
        String expectedVal = "test";

        assertThat(actualVal)
                .contains(expectedVal);
    }

    @Test
    public void authUserTest() {

        assertNotNull(userService.authUser("admin@email.com", "123"));

    }

    @Test
    public void createUserTest() {
        user.setUsername("josh");
        user.setEmail("josh@email.com");
        user.setPassword("123");
        when(userServiceMock.registerUser(user)).thenReturn("User: " + user.getUsername() + " created successfully");
        assertThat(userServiceMock.registerUser(user)).contains("josh");
        verify(userServiceMock).registerUser(user);

    }


}
