package org.spring.jpa.user.domain.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.DataSetFormat;
import com.github.database.rider.core.api.exporter.ExportDataSet;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.spring.jpa.user.SpringBaseTest;
import org.spring.jpa.user.domain.error.UserNotFoundException;
import org.spring.jpa.user.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.thenCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserRepoTest extends SpringBaseTest {


    final UserRepo userRepo;
    @Spy
    UserRepo userRepoMock;

    @Autowired
    EntityManager entityManager;


    User user;

    /**
     * JUnit5: Executes before each test
     */
    @BeforeEach
    void createNewUserInstance() {
        user = Instancio.create(User.class);

    }

    @Autowired
    private UserRepoTest(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Database Rider and AssertJ
     */
    @Test
    @DataSet("db/datasets/users.xml")
    void findUserByEmailTest() {
        Optional<User> result = userRepo.findByEmail("admin@example.com");
        assertThat(result.isEmpty()).isFalse();
    }

    /**
     * Database Rider and AssertJ
     */
    @Test
    @DataSet("db/datasets/users.xml")
    @ExportDataSet(format = DataSetFormat.XML, outputName = "target/exported/xml/allTables.xml")
    void findAllUsers() {
        List<User> result = userRepo.findAll();

        assertThat(result.size()).isEqualTo(3);
        assertThat(result).filteredOn(user -> user.getEmail().equals("admin@email.com")).isNotEmpty();
    }

    /**
     * Spring Test and AssertJ
     */
    @Test
    @Transactional
    void addUser() {
        userRepo.save(user);
        assertThat(userRepo.findByEmail(user.getEmail())).isPresent();
    }

    /**
     * Spring Test, Mockito, and AssertJ
     */
    @Test
    void testFindUserMock() {
//        when(userRepoMock.findByEmail(any(String.class))).thenThrow(new UserNotFoundException("User not found"));
        when(userRepoMock.findByEmail("badword@email.com")).thenThrow(new UserNotFoundException("User not found"));
        assertThat(userRepoMock.findByEmail("admin@email.com")).isNotNull();
        assertThatThrownBy(() -> userRepoMock.findByEmail("badword@email.com")).isInstanceOf(UserNotFoundException.class);
        verify(userRepoMock).findByEmail("badword@email.com");
        thenCode(() -> userRepoMock.findByEmail("badword@email.com")).doesNotThrowAnyException();
    }

    /**
     * Spring Test and AssertJ
     */
    @Transactional
    @Test
    void testFindUserEntityManager() {
        entityManager.persist(user);
        User result = entityManager.find(User.class, user.getId());
        assertThat(result.getEmail()).contains("bob@email.com");
    }


}
