package org.spring.unit.testing.presentation.user.domain.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.DataSetFormat;
import com.github.database.rider.core.api.exporter.ExportDataSet;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.spring.unit.testing.presentation.user.SpringBaseTest;
import org.spring.unit.testing.presentation.user.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserRepoTest extends SpringBaseTest {

    UserRepo userRepo;
    @Spy
    UserRepo userRepoMock;

    @Autowired
    EntityManager entityManager;


    User user;

    @BeforeEach
    void createNewUserInstance() {
        user = Instancio.create(User.class);

    }


    @Autowired
    private UserRepoTest(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Test
    @DataSet("users.yml")
    void findUserByEmailTest(){
        Optional<User> result = userRepo.findByEmail("admin@email.com");

        assertNotNull(result);
    }

    @Test
    @DataSet("users.yml")
    @ExportDataSet(format = DataSetFormat.XML,outputName="target/exported/xml/allTables.xml")
    void findAllUsers(){
        List<User> result = userRepo.findAll();
        assertNotNull(result);

        assertThat(result.size()).isEqualTo(3);

        assertThat(result.stream().filter(user -> user.getEmail()
                        .equals("admin@email.com"))).isNotNull();
    }



    @Test
    @Transactional
//    @ExpectedDataSet("expected_datasets/expected_users.yml")
    void addUser(){
        userRepo.save(user);
        assertThat(userRepo.findByEmail(user.getEmail())).isPresent();
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

    @Test
    void testFindUserMock(){
//        when(userRepoMock.findByEmail(any(String.class))).thenThrow(new RuntimeException("User not found"));
        when(userRepoMock.findByEmail("badword@email.com")).thenThrow(new RuntimeException("User not found"));
        assertThat(userRepoMock.findByEmail("admin@email.com")).isNotNull();
        assertThatThrownBy(() -> userRepoMock.findByEmail("badword@email.com")).isInstanceOf(RuntimeException.class);
        verify(userRepoMock).findByEmail("badword@email.com");
        assertThatCode(() -> userRepoMock.findByEmail("badword@email.com")).doesNotThrowAnyException();
    }

    @Transactional
    @Test
    void testFindUserEntityManager(){
        entityManager.persist(user);
        User result = entityManager.find(User.class,user.getId());
        assertThat(result.getEmail()).contains("bob@email.com");
    }



}
