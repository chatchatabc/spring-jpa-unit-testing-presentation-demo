package org.spring.jpa.user.impl.domain.service;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.spring.jpa.user.SpringBaseTest;
import org.spring.jpa.user.domain.model.User;
import org.spring.jpa.user.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserServiceImplTest extends SpringBaseTest {

    @Autowired
    private UserServiceImpl userService;

    @Mock
    UserService userServiceMock = mock(UserService.class);

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
        User user = new User();
        user.setUsername("josh");
        user.setEmail("josh@email.com");
        user.setPassword("123");
        when(userServiceMock.registerUser(user)).thenReturn(1000L);
        assertThat(userServiceMock.registerUser(user)).isEqualTo(1000L);
        verify(userServiceMock).registerUser(user);
    }

}
