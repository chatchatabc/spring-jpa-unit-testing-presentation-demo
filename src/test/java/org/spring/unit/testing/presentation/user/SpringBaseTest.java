package org.spring.unit.testing.presentation.user;

import com.github.database.rider.spring.api.DBRider;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DBRider
public class SpringBaseTest {
}
