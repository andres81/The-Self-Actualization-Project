package com.the_self_actualization_project.usermanagement.adapter.in.web.response;

import com.the_self_actualization_project.usermanagement.domain.OAuth2Provider;

public record UserInfoResponse(
    String username, String subject, OAuth2Provider provider, String email) {}
