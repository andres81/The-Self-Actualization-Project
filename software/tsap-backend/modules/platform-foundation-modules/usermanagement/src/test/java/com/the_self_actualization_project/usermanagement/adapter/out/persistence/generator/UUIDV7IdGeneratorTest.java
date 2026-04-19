package com.the_self_actualization_project.usermanagement.adapter.out.persistence.generator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class UUIDV7IdGeneratorTest {

  private final UUIDV7IdGenerator cut = new UUIDV7IdGenerator();

  @Test
  void generate() {

    var result = cut.generate(null, null);
    assertDoesNotThrow(() -> UUID.fromString(result.toString()));
  }
}
