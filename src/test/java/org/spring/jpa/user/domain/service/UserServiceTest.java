package org.spring.jpa.user.domain.service;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.spring.jpa.user.SpringBaseTest;
import org.spring.jpa.user.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
public class UserServiceTest extends SpringBaseTest {

    UserService userService;
    User user;

    @Mock
    UserService userServiceMock = mock(UserService.class);

    @BeforeEach
    void createNewInstance() {
        user = new User();
    }

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void sampleTest() {
        String actualVal = "tet123";
        String expectedVal = "test";

        assertThat(actualVal)
                .contains(expectedVal);
    }

    @Test
    @DataSet("db/datasets/users.xml")
    public void authUserTest() throws Exception {

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
