package com.the_self_actualization_project.qa;

import com.the_self_actualization_project.Application;
import org.springframework.boot.SpringApplication;

public class TestApplication {

  public static void main(String[] args) {
    SpringApplication.from(Application::main)
        .with(TestcontainersConfiguration.class).run(args);
  }

}

