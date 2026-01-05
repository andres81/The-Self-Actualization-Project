package com.the_self_actualization_project.usermanagement.application.port.in;

import com.the_self_actualization_project.usermanagement.domain.OAuth2Provider;

public record FindOrRegisterBySubjectCommand(String subject, String email, OAuth2Provider provider) {

}
