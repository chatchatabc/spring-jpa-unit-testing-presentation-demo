package org.spring.jpa.user;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.spring.api.DBRider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE, columnSensing = true, schema = "public" )
public class SpringBaseTest {
}
