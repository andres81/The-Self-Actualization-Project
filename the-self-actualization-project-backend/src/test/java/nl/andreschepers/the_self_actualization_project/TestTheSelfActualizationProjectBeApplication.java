package nl.andreschepers.the_self_actualization_project;

import org.springframework.boot.SpringApplication;

public class TestTheSelfActualizationProjectBeApplication {

	public static void main(String[] args) {
		SpringApplication.from(TheSelfActualizationProjectBeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

