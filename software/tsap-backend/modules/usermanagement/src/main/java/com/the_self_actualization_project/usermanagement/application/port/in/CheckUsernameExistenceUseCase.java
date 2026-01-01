package com.the_self_actualization_project.usermanagement.application.port.in;

public interface CheckUsernameExistenceUseCase {

  boolean doesUsernameExist(String username);
}