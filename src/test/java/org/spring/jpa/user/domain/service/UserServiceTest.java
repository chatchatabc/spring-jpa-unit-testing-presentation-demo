package org.spring.jpa.user.domain.service;

import com.github.database.rider.core.api.dataset.DataSet;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.spring.jpa.user.SpringBaseTest;
import org.spring.jpa.user.domain.error.UserNotFoundException;
import org.spring.jpa.user.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.thenCode;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserServiceTest extends SpringBaseTest {

    @Autowired
    private UserService userService;

    User user;

    @Mock
    final UserService userServiceMock = mock(UserService.class);

    /**
     * JUnit and Instancio
     * Before each test use Intancio to create a new instance of User
     */
    @BeforeEach
    public void createNewUserInstance() {
        user = Instancio.create(User.class);
    }

    /**
     * AssertJ
     * Sample Test
     */
    @Test
    public void sampleTest() {
        String actualVal = "tet123";
        String expectedVal = "test";

        assertThat(actualVal)
                .contains(expectedVal);
    }


    /**
     * Database Rider and JUnit5
     * Auth User Test
     */
    @Test
    @DataSet("db/datasets/users.xml")
    public void authUserTest() throws UserNotFoundException {
        assertNotNull(userService.authUser("admin@example.com", "123"));
    }

    /**
     * Database Rider and AssertJ
     * Register a user test
     */
    @Test
    public void registerUserTest() {
        thenCode(() -> userService.registerUser(user)).doesNotThrowAnyException();
    }

    /**
     * Mockito and AssertJ
     * Mock a creation of a user
     */
    @Test
    public void mockCreateUserTest() {
        when(userServiceMock.mockMethod(user)).thenReturn(1000L);
        assertThat(userServiceMock.mockMethod(user)).isEqualTo(1000L);
        verify(userServiceMock).mockMethod(user);
    }

}
