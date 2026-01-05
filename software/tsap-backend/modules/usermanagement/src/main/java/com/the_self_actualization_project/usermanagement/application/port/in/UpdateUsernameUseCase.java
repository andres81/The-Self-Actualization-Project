package com.the_self_actualization_project.usermanagement.application.port.in;

import java.util.UUID;

public interface UpdateUsernameUseCase {

  void updateUsername(UUID userId, String newUsername);
}
