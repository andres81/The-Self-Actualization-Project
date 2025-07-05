package nl.andreschepers.the_self_actualization_project.authentication.api.google.dto;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(@NotNull String googleIdToken) {}
