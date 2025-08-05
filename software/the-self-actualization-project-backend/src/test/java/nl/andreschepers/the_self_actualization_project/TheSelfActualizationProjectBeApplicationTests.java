package nl.andreschepers.the_self_actualization_project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TheSelfActualizationProjectBeApplicationTests {

	@Test
	void contextLoads() {
	}

}
